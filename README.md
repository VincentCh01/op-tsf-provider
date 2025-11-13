graph TD
    %% 初始阶段
    A[草稿] -->|提交审核| AR[审核中]
    AR -->|审核通过| B[待结算]
    AR -->|审核不通过| A

    %% 正常结算流程
    B -->|自动/手动结算| C[已结算]
    C -->|对账完成| D[已对账]
    D -->|归档| I[已归档]

    %% 异常处理：冲正（纠错）
    C -->|发现错误| E[冲正中]
    E -->|冲正成功| F[已冲正]
    E -->|冲正失败| C
    F -->|重新生成| A

    %% 逾期与强制
    B -->|逾期未结| G[逾期]
    G -->|强制结算| C
    G -->|强制作废| H[已作废]

    %% 作废流程
    A -->|主动作废| H
    B -->|主动作废| H
    C -->|业务取消| H
    H[已作废] -->|重新开单| A

    %% 样式统一（与前4个一致）
    style A fill:#e3f2fd,stroke:#1976d2
    style AR fill:#fff3e0,stroke:#f57c00
    style B fill:#fff3e0,stroke:#f57c00
    style C fill:#c8e6c9,stroke:#388e3c
    style D fill:#c8e6c9,stroke:#388e3c
    style E fill:#fff3e0,stroke:#f57c00
    style F fill:#ffcdd2,stroke:#c62828
    style G fill:#fff3e0,stroke:#f57c00
    style H fill:#ffcdd2,stroke:#c62828
    style I fill:#e8f5e9,stroke:#2e7d32
