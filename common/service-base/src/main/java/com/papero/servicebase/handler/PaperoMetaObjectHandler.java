package com.papero.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class PaperoMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"gmtCreate", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,"gmtModified", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject,"isDeleted", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"gmtModified", LocalDateTime.class, LocalDateTime.now());
    }
}
