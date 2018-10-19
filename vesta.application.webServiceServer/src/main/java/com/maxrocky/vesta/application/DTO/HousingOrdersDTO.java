package com.maxrocky.vesta.application.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/2/8.
 */
public class HousingOrdersDTO {
    private List<HouseTransferDTO> houseTransfer;//正式交房
    private List<HouseOpenDTO> houseOpen;//工地开放
    private List<InternalacceptanceDTO> internalacceptance;//内部预验

    public HousingOrdersDTO(){
        this.houseOpen=new ArrayList<>();
        this.houseTransfer=new ArrayList<>();
        this.internalacceptance=new ArrayList<>();
    }
    public List<HouseTransferDTO> getHouseTransfer() {
        return houseTransfer;
    }

    public void setHouseTransfer(List<HouseTransferDTO> houseTransfer) {
        this.houseTransfer = houseTransfer;
    }

    public List<HouseOpenDTO> getHouseOpen() {
        return houseOpen;
    }

    public void setHouseOpen(List<HouseOpenDTO> houseOpen) {
        this.houseOpen = houseOpen;
    }


    public List<InternalacceptanceDTO> getInternalacceptance() {
        return internalacceptance;
    }

    public void setInternalacceptance(List<InternalacceptanceDTO> internalacceptance) {
        this.internalacceptance = internalacceptance;
    }
}
