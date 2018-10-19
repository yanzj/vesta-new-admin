/**
 * TradeHeader.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.model;
public class TradeHeader  implements java.io.Serializable {
    private java.lang.Integer adduppoints;

    private java.lang.String buffer1;

    private java.lang.String buffer2;

    private java.lang.String buffer3;

    private java.math.BigDecimal buffer4;

    private java.math.BigDecimal buffer5;

    private java.math.BigDecimal buffer6;

    private java.util.Calendar buffer7;

    private java.util.Calendar buffer8;

    private java.util.Calendar buffer9;

    private java.util.Calendar effectiveordertime;

    private java.lang.String memberid;

    private java.util.Calendar ordercompletedtime;

    private java.util.Calendar ordercreated;

    private java.util.Calendar ordersettlementtime;

    private java.lang.String orderstate;

    private java.lang.String ordertype;

    private java.lang.String projectname;

    private java.lang.String shopname;

    private TradeItems[] tradeitems;

    private java.lang.String tradingorderid;

    private java.lang.String tradingtype;

    private java.math.BigDecimal usemoney;

    private java.lang.Integer usepoints;

    public TradeHeader() {
    }

    public TradeHeader(
           java.lang.Integer adduppoints,
           java.lang.String buffer1,
           java.lang.String buffer2,
           java.lang.String buffer3,
           java.math.BigDecimal buffer4,
           java.math.BigDecimal buffer5,
           java.math.BigDecimal buffer6,
           java.util.Calendar buffer7,
           java.util.Calendar buffer8,
           java.util.Calendar buffer9,
           java.util.Calendar effectiveordertime,
           java.lang.String memberid,
           java.util.Calendar ordercompletedtime,
           java.util.Calendar ordercreated,
           java.util.Calendar ordersettlementtime,
           java.lang.String orderstate,
           java.lang.String ordertype,
           java.lang.String projectname,
           java.lang.String shopname,
           TradeItems[] tradeitems,
           java.lang.String tradingorderid,
           java.lang.String tradingtype,
           java.math.BigDecimal usemoney,
           java.lang.Integer usepoints) {
           this.adduppoints = adduppoints;
           this.buffer1 = buffer1;
           this.buffer2 = buffer2;
           this.buffer3 = buffer3;
           this.buffer4 = buffer4;
           this.buffer5 = buffer5;
           this.buffer6 = buffer6;
           this.buffer7 = buffer7;
           this.buffer8 = buffer8;
           this.buffer9 = buffer9;
           this.effectiveordertime = effectiveordertime;
           this.memberid = memberid;
           this.ordercompletedtime = ordercompletedtime;
           this.ordercreated = ordercreated;
           this.ordersettlementtime = ordersettlementtime;
           this.orderstate = orderstate;
           this.ordertype = ordertype;
           this.projectname = projectname;
           this.shopname = shopname;
           this.tradeitems = tradeitems;
           this.tradingorderid = tradingorderid;
           this.tradingtype = tradingtype;
           this.usemoney = usemoney;
           this.usepoints = usepoints;
    }


    /**
     * Gets the adduppoints value for this TradeHeader.
     * 
     * @return adduppoints
     */
    public java.lang.Integer getAdduppoints() {
        return adduppoints;
    }


    /**
     * Sets the adduppoints value for this TradeHeader.
     * 
     * @param adduppoints
     */
    public void setAdduppoints(java.lang.Integer adduppoints) {
        this.adduppoints = adduppoints;
    }


    /**
     * Gets the buffer1 value for this TradeHeader.
     * 
     * @return buffer1
     */
    public java.lang.String getBuffer1() {
        return buffer1;
    }


    /**
     * Sets the buffer1 value for this TradeHeader.
     * 
     * @param buffer1
     */
    public void setBuffer1(java.lang.String buffer1) {
        this.buffer1 = buffer1;
    }


    /**
     * Gets the buffer2 value for this TradeHeader.
     * 
     * @return buffer2
     */
    public java.lang.String getBuffer2() {
        return buffer2;
    }


    /**
     * Sets the buffer2 value for this TradeHeader.
     * 
     * @param buffer2
     */
    public void setBuffer2(java.lang.String buffer2) {
        this.buffer2 = buffer2;
    }


    /**
     * Gets the buffer3 value for this TradeHeader.
     * 
     * @return buffer3
     */
    public java.lang.String getBuffer3() {
        return buffer3;
    }


    /**
     * Sets the buffer3 value for this TradeHeader.
     * 
     * @param buffer3
     */
    public void setBuffer3(java.lang.String buffer3) {
        this.buffer3 = buffer3;
    }


    /**
     * Gets the buffer4 value for this TradeHeader.
     * 
     * @return buffer4
     */
    public java.math.BigDecimal getBuffer4() {
        return buffer4;
    }


    /**
     * Sets the buffer4 value for this TradeHeader.
     * 
     * @param buffer4
     */
    public void setBuffer4(java.math.BigDecimal buffer4) {
        this.buffer4 = buffer4;
    }


    /**
     * Gets the buffer5 value for this TradeHeader.
     * 
     * @return buffer5
     */
    public java.math.BigDecimal getBuffer5() {
        return buffer5;
    }


    /**
     * Sets the buffer5 value for this TradeHeader.
     * 
     * @param buffer5
     */
    public void setBuffer5(java.math.BigDecimal buffer5) {
        this.buffer5 = buffer5;
    }


    /**
     * Gets the buffer6 value for this TradeHeader.
     * 
     * @return buffer6
     */
    public java.math.BigDecimal getBuffer6() {
        return buffer6;
    }


    /**
     * Sets the buffer6 value for this TradeHeader.
     * 
     * @param buffer6
     */
    public void setBuffer6(java.math.BigDecimal buffer6) {
        this.buffer6 = buffer6;
    }


    /**
     * Gets the buffer7 value for this TradeHeader.
     * 
     * @return buffer7
     */
    public java.util.Calendar getBuffer7() {
        return buffer7;
    }


    /**
     * Sets the buffer7 value for this TradeHeader.
     * 
     * @param buffer7
     */
    public void setBuffer7(java.util.Calendar buffer7) {
        this.buffer7 = buffer7;
    }


    /**
     * Gets the buffer8 value for this TradeHeader.
     * 
     * @return buffer8
     */
    public java.util.Calendar getBuffer8() {
        return buffer8;
    }


    /**
     * Sets the buffer8 value for this TradeHeader.
     * 
     * @param buffer8
     */
    public void setBuffer8(java.util.Calendar buffer8) {
        this.buffer8 = buffer8;
    }


    /**
     * Gets the buffer9 value for this TradeHeader.
     * 
     * @return buffer9
     */
    public java.util.Calendar getBuffer9() {
        return buffer9;
    }


    /**
     * Sets the buffer9 value for this TradeHeader.
     * 
     * @param buffer9
     */
    public void setBuffer9(java.util.Calendar buffer9) {
        this.buffer9 = buffer9;
    }


    /**
     * Gets the effectiveordertime value for this TradeHeader.
     * 
     * @return effectiveordertime
     */
    public java.util.Calendar getEffectiveordertime() {
        return effectiveordertime;
    }


    /**
     * Sets the effectiveordertime value for this TradeHeader.
     * 
     * @param effectiveordertime
     */
    public void setEffectiveordertime(java.util.Calendar effectiveordertime) {
        this.effectiveordertime = effectiveordertime;
    }


    /**
     * Gets the memberid value for this TradeHeader.
     * 
     * @return memberid
     */
    public java.lang.String getMemberid() {
        return memberid;
    }


    /**
     * Sets the memberid value for this TradeHeader.
     * 
     * @param memberid
     */
    public void setMemberid(java.lang.String memberid) {
        this.memberid = memberid;
    }


    /**
     * Gets the ordercompletedtime value for this TradeHeader.
     * 
     * @return ordercompletedtime
     */
    public java.util.Calendar getOrdercompletedtime() {
        return ordercompletedtime;
    }


    /**
     * Sets the ordercompletedtime value for this TradeHeader.
     * 
     * @param ordercompletedtime
     */
    public void setOrdercompletedtime(java.util.Calendar ordercompletedtime) {
        this.ordercompletedtime = ordercompletedtime;
    }


    /**
     * Gets the ordercreated value for this TradeHeader.
     * 
     * @return ordercreated
     */
    public java.util.Calendar getOrdercreated() {
        return ordercreated;
    }


    /**
     * Sets the ordercreated value for this TradeHeader.
     * 
     * @param ordercreated
     */
    public void setOrdercreated(java.util.Calendar ordercreated) {
        this.ordercreated = ordercreated;
    }


    /**
     * Gets the ordersettlementtime value for this TradeHeader.
     * 
     * @return ordersettlementtime
     */
    public java.util.Calendar getOrdersettlementtime() {
        return ordersettlementtime;
    }


    /**
     * Sets the ordersettlementtime value for this TradeHeader.
     * 
     * @param ordersettlementtime
     */
    public void setOrdersettlementtime(java.util.Calendar ordersettlementtime) {
        this.ordersettlementtime = ordersettlementtime;
    }


    /**
     * Gets the orderstate value for this TradeHeader.
     * 
     * @return orderstate
     */
    public java.lang.String getOrderstate() {
        return orderstate;
    }


    /**
     * Sets the orderstate value for this TradeHeader.
     * 
     * @param orderstate
     */
    public void setOrderstate(java.lang.String orderstate) {
        this.orderstate = orderstate;
    }


    /**
     * Gets the ordertype value for this TradeHeader.
     * 
     * @return ordertype
     */
    public java.lang.String getOrdertype() {
        return ordertype;
    }


    /**
     * Sets the ordertype value for this TradeHeader.
     * 
     * @param ordertype
     */
    public void setOrdertype(java.lang.String ordertype) {
        this.ordertype = ordertype;
    }


    /**
     * Gets the projectname value for this TradeHeader.
     * 
     * @return projectname
     */
    public java.lang.String getProjectname() {
        return projectname;
    }


    /**
     * Sets the projectname value for this TradeHeader.
     * 
     * @param projectname
     */
    public void setProjectname(java.lang.String projectname) {
        this.projectname = projectname;
    }


    /**
     * Gets the shopname value for this TradeHeader.
     * 
     * @return shopname
     */
    public java.lang.String getShopname() {
        return shopname;
    }


    /**
     * Sets the shopname value for this TradeHeader.
     * 
     * @param shopname
     */
    public void setShopname(java.lang.String shopname) {
        this.shopname = shopname;
    }


    /**
     * Gets the tradeitems value for this TradeHeader.
     * 
     * @return tradeitems
     */
    public TradeItems[] getTradeitems() {
        return tradeitems;
    }


    /**
     * Sets the tradeitems value for this TradeHeader.
     * 
     * @param tradeitems
     */
    public void setTradeitems(TradeItems[] tradeitems) {
        this.tradeitems = tradeitems;
    }


    /**
     * Gets the tradingorderid value for this TradeHeader.
     * 
     * @return tradingorderid
     */
    public java.lang.String getTradingorderid() {
        return tradingorderid;
    }


    /**
     * Sets the tradingorderid value for this TradeHeader.
     * 
     * @param tradingorderid
     */
    public void setTradingorderid(java.lang.String tradingorderid) {
        this.tradingorderid = tradingorderid;
    }


    /**
     * Gets the tradingtype value for this TradeHeader.
     * 
     * @return tradingtype
     */
    public java.lang.String getTradingtype() {
        return tradingtype;
    }


    /**
     * Sets the tradingtype value for this TradeHeader.
     * 
     * @param tradingtype
     */
    public void setTradingtype(java.lang.String tradingtype) {
        this.tradingtype = tradingtype;
    }


    /**
     * Gets the usemoney value for this TradeHeader.
     * 
     * @return usemoney
     */
    public java.math.BigDecimal getUsemoney() {
        return usemoney;
    }


    /**
     * Sets the usemoney value for this TradeHeader.
     * 
     * @param usemoney
     */
    public void setUsemoney(java.math.BigDecimal usemoney) {
        this.usemoney = usemoney;
    }


    /**
     * Gets the usepoints value for this TradeHeader.
     * 
     * @return usepoints
     */
    public java.lang.Integer getUsepoints() {
        return usepoints;
    }


    /**
     * Sets the usepoints value for this TradeHeader.
     * 
     * @param usepoints
     */
    public void setUsepoints(java.lang.Integer usepoints) {
        this.usepoints = usepoints;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TradeHeader)) return false;
        TradeHeader other = (TradeHeader) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.adduppoints==null && other.getAdduppoints()==null) || 
             (this.adduppoints!=null &&
              this.adduppoints.equals(other.getAdduppoints()))) &&
            ((this.buffer1==null && other.getBuffer1()==null) || 
             (this.buffer1!=null &&
              this.buffer1.equals(other.getBuffer1()))) &&
            ((this.buffer2==null && other.getBuffer2()==null) || 
             (this.buffer2!=null &&
              this.buffer2.equals(other.getBuffer2()))) &&
            ((this.buffer3==null && other.getBuffer3()==null) || 
             (this.buffer3!=null &&
              this.buffer3.equals(other.getBuffer3()))) &&
            ((this.buffer4==null && other.getBuffer4()==null) || 
             (this.buffer4!=null &&
              this.buffer4.equals(other.getBuffer4()))) &&
            ((this.buffer5==null && other.getBuffer5()==null) || 
             (this.buffer5!=null &&
              this.buffer5.equals(other.getBuffer5()))) &&
            ((this.buffer6==null && other.getBuffer6()==null) || 
             (this.buffer6!=null &&
              this.buffer6.equals(other.getBuffer6()))) &&
            ((this.buffer7==null && other.getBuffer7()==null) || 
             (this.buffer7!=null &&
              this.buffer7.equals(other.getBuffer7()))) &&
            ((this.buffer8==null && other.getBuffer8()==null) || 
             (this.buffer8!=null &&
              this.buffer8.equals(other.getBuffer8()))) &&
            ((this.buffer9==null && other.getBuffer9()==null) || 
             (this.buffer9!=null &&
              this.buffer9.equals(other.getBuffer9()))) &&
            ((this.effectiveordertime==null && other.getEffectiveordertime()==null) || 
             (this.effectiveordertime!=null &&
              this.effectiveordertime.equals(other.getEffectiveordertime()))) &&
            ((this.memberid==null && other.getMemberid()==null) || 
             (this.memberid!=null &&
              this.memberid.equals(other.getMemberid()))) &&
            ((this.ordercompletedtime==null && other.getOrdercompletedtime()==null) || 
             (this.ordercompletedtime!=null &&
              this.ordercompletedtime.equals(other.getOrdercompletedtime()))) &&
            ((this.ordercreated==null && other.getOrdercreated()==null) || 
             (this.ordercreated!=null &&
              this.ordercreated.equals(other.getOrdercreated()))) &&
            ((this.ordersettlementtime==null && other.getOrdersettlementtime()==null) || 
             (this.ordersettlementtime!=null &&
              this.ordersettlementtime.equals(other.getOrdersettlementtime()))) &&
            ((this.orderstate==null && other.getOrderstate()==null) || 
             (this.orderstate!=null &&
              this.orderstate.equals(other.getOrderstate()))) &&
            ((this.ordertype==null && other.getOrdertype()==null) || 
             (this.ordertype!=null &&
              this.ordertype.equals(other.getOrdertype()))) &&
            ((this.projectname==null && other.getProjectname()==null) || 
             (this.projectname!=null &&
              this.projectname.equals(other.getProjectname()))) &&
            ((this.shopname==null && other.getShopname()==null) || 
             (this.shopname!=null &&
              this.shopname.equals(other.getShopname()))) &&
            ((this.tradeitems==null && other.getTradeitems()==null) || 
             (this.tradeitems!=null &&
              java.util.Arrays.equals(this.tradeitems, other.getTradeitems()))) &&
            ((this.tradingorderid==null && other.getTradingorderid()==null) || 
             (this.tradingorderid!=null &&
              this.tradingorderid.equals(other.getTradingorderid()))) &&
            ((this.tradingtype==null && other.getTradingtype()==null) || 
             (this.tradingtype!=null &&
              this.tradingtype.equals(other.getTradingtype()))) &&
            ((this.usemoney==null && other.getUsemoney()==null) || 
             (this.usemoney!=null &&
              this.usemoney.equals(other.getUsemoney()))) &&
            ((this.usepoints==null && other.getUsepoints()==null) || 
             (this.usepoints!=null &&
              this.usepoints.equals(other.getUsepoints())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAdduppoints() != null) {
            _hashCode += getAdduppoints().hashCode();
        }
        if (getBuffer1() != null) {
            _hashCode += getBuffer1().hashCode();
        }
        if (getBuffer2() != null) {
            _hashCode += getBuffer2().hashCode();
        }
        if (getBuffer3() != null) {
            _hashCode += getBuffer3().hashCode();
        }
        if (getBuffer4() != null) {
            _hashCode += getBuffer4().hashCode();
        }
        if (getBuffer5() != null) {
            _hashCode += getBuffer5().hashCode();
        }
        if (getBuffer6() != null) {
            _hashCode += getBuffer6().hashCode();
        }
        if (getBuffer7() != null) {
            _hashCode += getBuffer7().hashCode();
        }
        if (getBuffer8() != null) {
            _hashCode += getBuffer8().hashCode();
        }
        if (getBuffer9() != null) {
            _hashCode += getBuffer9().hashCode();
        }
        if (getEffectiveordertime() != null) {
            _hashCode += getEffectiveordertime().hashCode();
        }
        if (getMemberid() != null) {
            _hashCode += getMemberid().hashCode();
        }
        if (getOrdercompletedtime() != null) {
            _hashCode += getOrdercompletedtime().hashCode();
        }
        if (getOrdercreated() != null) {
            _hashCode += getOrdercreated().hashCode();
        }
        if (getOrdersettlementtime() != null) {
            _hashCode += getOrdersettlementtime().hashCode();
        }
        if (getOrderstate() != null) {
            _hashCode += getOrderstate().hashCode();
        }
        if (getOrdertype() != null) {
            _hashCode += getOrdertype().hashCode();
        }
        if (getProjectname() != null) {
            _hashCode += getProjectname().hashCode();
        }
        if (getShopname() != null) {
            _hashCode += getShopname().hashCode();
        }
        if (getTradeitems() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTradeitems());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTradeitems(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTradingorderid() != null) {
            _hashCode += getTradingorderid().hashCode();
        }
        if (getTradingtype() != null) {
            _hashCode += getTradingtype().hashCode();
        }
        if (getUsemoney() != null) {
            _hashCode += getUsemoney().hashCode();
        }
        if (getUsepoints() != null) {
            _hashCode += getUsepoints().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TradeHeader.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeHeader"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adduppoints");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Adduppoints"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer7");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer8");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("buffer9");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Buffer9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("effectiveordertime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Effectiveordertime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Memberid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordercompletedtime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Ordercompletedtime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordercreated");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Ordercreated"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordersettlementtime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Ordersettlementtime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orderstate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Orderstate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ordertype");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Ordertype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("projectname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Projectname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shopname");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Shopname"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeitems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Tradeitems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeItems"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "TradeItems"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradingorderid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Tradingorderid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradingtype");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Tradingtype"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usemoney");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Usemoney"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usepoints");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "Usepoints"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
