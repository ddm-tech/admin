package com.ddm.admin.base.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageTableRequest implements Serializable {

    private Integer page;
    private Integer limit;
    private Integer offset;

    public void countOffset(){
        if(null==this.page || this.limit==null){
            this.offset=0;

        }
        this.offset=(this.page-1)*limit;
    }

}
