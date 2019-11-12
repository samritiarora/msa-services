CREATE TABLE IF NOT EXISTS TB_CHECKBOOK_ORDER (
    id                        BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id                        VARCHAR(36)       NOT NULL,
    account_id                     VARCHAR(36)       NOT NULL,
    ch_bk_status                         VARCHAR(276)      NOT NULL,
    created_by                     VARCHAR(276)      NOT NULL,
    modified_by                    VARCHAR(276)      NOT NULL,
    date_created                   TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_modified                  TIMESTAMP         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);