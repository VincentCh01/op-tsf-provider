<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>JVM Dashboard</title>
  <link rel="preconnect" href="https://cdn.jsdelivr.net">
  <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns@3"></script>
  <style>
    body { font-family: system-ui, sans-serif; margin: 16px; }
    .toolbar { display: flex; align-items: center; justify-content: space-between; margin-bottom: 12px; }
    .controls { display: flex; gap: 8px; align-items: center; }
    .controls button { padding: 6px 10px; border: 1px solid #ccc; background: #fff; border-radius: 6px; cursor: pointer; }
    .controls button.active { background: #f0f0f0; }
    .grid { display: grid; gap: 16px; }
    .grid.small { grid-template-columns: repeat(4, minmax(240px, 1fr)); }
    .grid.medium { grid-template-columns: repeat(2, minmax(320px, 1fr)); }
    .grid.large { grid-template-columns: repeat(1, minmax(480px, 1fr)); }
    .card { border: 1px solid #ddd; border-radius: 8px; padding: 12px; box-shadow: 0 1px 3px rgba(0,0,0,0.06); }
    h2 { margin: 0 0 8px; font-size: 16px; }
    #status { }
    canvas { max-width: 100%; }
  </style>
</head>
<body>
  <h1>JVM Dashboard</h1>
  <div class="toolbar">
    <div id="status">Loading...</div>
    <div class="controls">
      <span>Size:</span>
      <button id="sizeSmall">Small</button>
      <button id="sizeMedium" class="active">Medium</button>
      <button id="sizeLarge">Large</button>
    </div>
  </div>
  <div id="dashboard" class="grid medium">
    <!-- Heap -->
    <div class="card"><h2>Heap Used (MB)</h2><canvas id="heapUsed"></canvas></div>
    <div class="card"><h2>Heap Committed (MB)</h2><canvas id="heapCommitted"></canvas></div>
    <div class="card"><h2>Heap Max (MB)</h2><canvas id="heapMax"></canvas></div>
    <!-- Non-Heap -->
    <div class="card"><h2>Non-Heap Used (MB)</h2><canvas id="nonHeapUsed"></canvas></div>
    <div class="card"><h2>Non-Heap Committed (MB)</h2><canvas id="nonHeapCommitted"></canvas></div>
    <div class="card"><h2>Non-Heap Max (MB)</h2><canvas id="nonHeapMax"></canvas></div>
    <!-- Threads -->
    <div class="card"><h2>Threads Total</h2><canvas id="threadsTotal"></canvas></div>
    <div class="card"><h2>Threads Daemon</h2><canvas id="threadsDaemon"></canvas></div>
    <div class="card"><h2>Threads Peak</h2><canvas id="threadsPeak"></canvas></div>
    <div class="card"><h2>Total Started Threads</h2><canvas id="threadsStarted"></canvas></div>
    <!-- Classes -->
    <div class="card"><h2>Loaded Class Count</h2><canvas id="classesLoaded"></canvas></div>
    <div class="card"><h2>Total Loaded Class Count</h2><canvas id="classesTotalLoaded"></canvas></div>
    <div class="card"><h2>Unloaded Class Count</h2><canvas id="classesUnloaded"></canvas></div>
    <!-- GC -->
    <div class="card"><h2>GC Collection Count</h2><canvas id="gcCount"></canvas></div>
    <div class="card"><h2>GC Collection Time (ms)</h2><canvas id="gcTime"></canvas></div>
  </div>

  <script>
    const api = new URLSearchParams(location.search).get('api') || '/jvm/';
    const statusEl = document.getElementById('status');
    const startTime = Date.now();
    let currentUnit = 'second';
    function computeUnit() {
      const elapsedMs = Date.now() - startTime;
      const elapsedMin = elapsedMs / 60000;
      if (elapsedMin < 1) return 'second';
      if (elapsedMin < 120) return 'minute';
      if (elapsedMin < 2880) return 'hour';
      return 'day';
    }

    function mb(n) { return n > 0 ? (n / (1024*1024)) : 0; }

    // Utility functions
    function makeLineChart(id, label, color) {
      return new Chart(document.getElementById(id), {
        type: 'line',
        data: { datasets: [{ label, data: [], borderColor: color }] },
        options: {
          animation: false,
          scales: {
            x: { type: 'time', time: { unit: currentUnit } },
            y: { beginAtZero: true }
          },
          plugins: { decimation: { enabled: true, algorithm: 'min-max' } },
          spanGaps: true
        }
      });
    }

    // Create charts
    const heapUsedChart = makeLineChart('heapUsed', 'used', '#1976d2');
    const heapCommittedChart = makeLineChart('heapCommitted', 'committed', '#1565c0');
    const heapMaxChart = makeLineChart('heapMax', 'max', '#0d47a1');

    const nonHeapUsedChart = makeLineChart('nonHeapUsed', 'used', '#388e3c');
    const nonHeapCommittedChart = makeLineChart('nonHeapCommitted', 'committed', '#2e7d32');
    const nonHeapMaxChart = makeLineChart('nonHeapMax', 'max', '#1b5e20');

    const threadsTotalChart = makeLineChart('threadsTotal', 'total', '#f57c00');
    const threadsDaemonChart = makeLineChart('threadsDaemon', 'daemon', '#fb8c00');
    const threadsPeakChart = makeLineChart('threadsPeak', 'peak', '#ef6c00');
    const threadsStartedChart = makeLineChart('threadsStarted', 'totalStarted', '#e65100');

    const classesLoadedChart = makeLineChart('classesLoaded', 'loaded', '#7e57c2');
    const classesTotalLoadedChart = makeLineChart('classesTotalLoaded', 'totalLoaded', '#5e35b1');
    const classesUnloadedChart = makeLineChart('classesUnloaded', 'unloaded', '#4527a0');

    const gcCountChart = new Chart(document.getElementById('gcCount'), {
      type: 'bar',
      data: { labels: [], datasets: [{ label: 'collectionCount', data: [], backgroundColor: '#7b1fa2' }] },
      options: { animation: false, scales: { y: { beginAtZero: true } } }
    });
    const gcTimeChart = new Chart(document.getElementById('gcTime'), {
      type: 'bar',
      data: { labels: [], datasets: [{ label: 'collectionTime', data: [], backgroundColor: '#ab47bc' }] },
      options: { animation: false, scales: { y: { beginAtZero: true } } }
    });

    function updateCharts(info) {
      const now = new Date();
      const unit = computeUnit();
      if (unit !== currentUnit) {
        currentUnit = unit;
        [
          heapUsedChart, heapCommittedChart, heapMaxChart,
          nonHeapUsedChart, nonHeapCommittedChart, nonHeapMaxChart,
          threadsTotalChart, threadsDaemonChart, threadsPeakChart, threadsStartedChart,
          classesLoadedChart, classesTotalLoadedChart, classesUnloadedChart
        ].forEach(ch => { ch.options.scales.x.time.unit = currentUnit; });
      }

      // Heap
      heapUsedChart.data.datasets[0].data.push({ x: now, y: mb(info.heap?.used || 0) });
      heapUsedChart.update();

      heapCommittedChart.data.datasets[0].data.push({ x: now, y: mb(info.heap?.committed || 0) });
      heapCommittedChart.update();

      heapMaxChart.data.datasets[0].data.push({ x: now, y: mb(info.heap?.max || 0) });
      heapMaxChart.update();

      // Non-Heap
      nonHeapUsedChart.data.datasets[0].data.push({ x: now, y: mb(info.nonHeap?.used || 0) });
      nonHeapUsedChart.update();

      nonHeapCommittedChart.data.datasets[0].data.push({ x: now, y: mb(info.nonHeap?.committed || 0) });
      nonHeapCommittedChart.update();

      nonHeapMaxChart.data.datasets[0].data.push({ x: now, y: mb(info.nonHeap?.max || 0) });
      nonHeapMaxChart.update();

      // Threads
      threadsTotalChart.data.datasets[0].data.push({ x: now, y: (info.threadCount || 0) });
      threadsTotalChart.update();

      threadsDaemonChart.data.datasets[0].data.push({ x: now, y: (info.daemonThreadCount || 0) });
      threadsDaemonChart.update();

      threadsPeakChart.data.datasets[0].data.push({ x: now, y: (info.peakThreadCount || 0) });
      threadsPeakChart.update();

      threadsStartedChart.data.datasets[0].data.push({ x: now, y: (info.totalStartedThreadCount || 0) });
      threadsStartedChart.update();

      // Classes
      classesLoadedChart.data.datasets[0].data.push({ x: now, y: (info.loadedClassCount || 0) });
      classesLoadedChart.update();

      classesTotalLoadedChart.data.datasets[0].data.push({ x: now, y: (info.totalLoadedClassCount || 0) });
      classesTotalLoadedChart.update();

      classesUnloadedChart.data.datasets[0].data.push({ x: now, y: (info.unloadedClassCount || 0) });
      classesUnloadedChart.update();

      // GC aggregated by name; show collectionCount (latest snapshot)
      const gcNames = (info.garbageCollectors || []).map(g => g.name);
      const gcCounts = (info.garbageCollectors || []).map(g => g.collectionCount || 0);
      const gcTimes = (info.garbageCollectors || []).map(g => g.collectionTime || 0);
      gcCountChart.data.labels = gcNames;
      gcCountChart.data.datasets[0].data = gcCounts;
      gcCountChart.update();

      gcTimeChart.data.labels = gcNames;
      gcTimeChart.data.datasets[0].data = gcTimes;
      gcTimeChart.update();
    }

    async function tick() {
      try {
        const resp = await fetch(api, { cache: 'no-store' });
        const json = await resp.json();
        if (!json.enabled) {
          statusEl.textContent = 'Endpoint disabled';
          return;
        }
        statusEl.textContent = `VM: ${json.vmName} | uptime: ${json.uptime}ms`;
        updateCharts(json);
      } catch (e) {
        statusEl.textContent = 'Failed to fetch data: ' + e.message;
      }
    }

    setInterval(tick, 1000);
    tick();

    // Layout size switch
    const dashboard = document.getElementById('dashboard');
    const btnSmall = document.getElementById('sizeSmall');
    const btnMedium = document.getElementById('sizeMedium');
    const btnLarge = document.getElementById('sizeLarge');
    function setSize(size) {
      dashboard.classList.remove('small','medium','large');
      dashboard.classList.add(size);
      [btnSmall, btnMedium, btnLarge].forEach(b => b.classList.remove('active'));
      if (size === 'small') btnSmall.classList.add('active');
      if (size === 'medium') btnMedium.classList.add('active');
      if (size === 'large') btnLarge.classList.add('active');
    }
    btnSmall.addEventListener('click', () => setSize('small'));
    btnMedium.addEventListener('click', () => setSize('medium'));
    btnLarge.addEventListener('click', () => setSize('large'));
  </script>
</body>
</html>
