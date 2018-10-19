package com.maxrocky.vesta.application.DTO.json.HI0009;

import java.util.List;

/**
 * Created by Magic on 2016/5/6.
 */
public class RectifiReturnJsomDTO {
    private List repair;
    private List repairing;
    private List repairend;
    private List repairList;


    public RectifiReturnJsomDTO(List repair,List repairing,List repairend,List repairList){
        this.repair=repair;
        this.repairing=repairing;
        this.repairend=repairend;
        this.repairList=repairList;
    }
    public RectifiReturnJsomDTO(){
        repair=null;
        repairing=null;
        repairend=null;
        repairList=null;
    }

    public List getRepair() {
        return repair;
    }

    public void setRepair(List repair) {
        this.repair = repair;
    }

    public List getRepairing() {
        return repairing;
    }

    public void setRepairing(List repairing) {
        this.repairing = repairing;
    }

    public List getRepairend() {
        return repairend;
    }

    public void setRepairend(List repairend) {
        this.repairend = repairend;
    }

    public List getRepairList() {
        return repairList;
    }

    public void setRepairList(List repairList) {
        this.repairList = repairList;
    }
}
