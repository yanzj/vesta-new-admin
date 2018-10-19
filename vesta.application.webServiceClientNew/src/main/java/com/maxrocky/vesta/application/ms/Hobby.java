/**
 * Hobby.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.maxrocky.vesta.application.ms;

public class Hobby implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected Hobby(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Music = "Music";
    public static final java.lang.String _Dancing = "Dancing";
    public static final java.lang.String _Drawing = "Drawing";
    public static final java.lang.String _Handwriting = "Handwriting";
    public static final java.lang.String _Movies = "Movies";
    public static final java.lang.String _Photography = "Photography";
    public static final java.lang.String _literature = "literature";
    public static final java.lang.String _Bodybuilding = "Bodybuilding";
    public static final java.lang.String _Running = "Running";
    public static final java.lang.String _climbing = "climbing";
    public static final java.lang.String _RockClimbing = "RockClimbing";
    public static final java.lang.String _Yoga = "Yoga";
    public static final java.lang.String _Fighting = "Fighting";
    public static final java.lang.String _Riding = "Riding";
    public static final java.lang.String _Pingpong = "Pingpong";
    public static final java.lang.String _Badminton = "Badminton";
    public static final java.lang.String _Tennis = "Tennis";
    public static final java.lang.String _Basketball = "Basketball";
    public static final java.lang.String _Football = "Football";
    public static final java.lang.String _Swimming = "Swimming";
    public static final java.lang.String _Golf = "Golf";
    public static final java.lang.String _Bowling = "Bowling";
    public static final java.lang.String _PC = "PC";
    public static final java.lang.String _HandiCraftArt = "HandiCraftArt";
    public static final java.lang.String _antique = "antique";
    public static final java.lang.String _AlcoholandTobacco = "AlcoholandTobacco";
    public static final java.lang.String _Tea = "Tea";
    public static final java.lang.String _Cooking = "Cooking";
    public static final java.lang.String _Travel = "Travel";
    public static final java.lang.String _Finance = "Finance";
    public static final java.lang.String _Digital = "Digital";
    public static final java.lang.String _Car = "Car";
    public static final java.lang.String _Cosmetology = "Cosmetology";
    public static final java.lang.String _Chess = "Chess";
    public static final java.lang.String _Shopping = "Shopping";
    public static final java.lang.String _Luxury = "Luxury";
    public static final java.lang.String _Others = "Others";
    public static final Hobby Music = new Hobby(_Music);
    public static final Hobby Dancing = new Hobby(_Dancing);
    public static final Hobby Drawing = new Hobby(_Drawing);
    public static final Hobby Handwriting = new Hobby(_Handwriting);
    public static final Hobby Movies = new Hobby(_Movies);
    public static final Hobby Photography = new Hobby(_Photography);
    public static final Hobby literature = new Hobby(_literature);
    public static final Hobby Bodybuilding = new Hobby(_Bodybuilding);
    public static final Hobby Running = new Hobby(_Running);
    public static final Hobby climbing = new Hobby(_climbing);
    public static final Hobby RockClimbing = new Hobby(_RockClimbing);
    public static final Hobby Yoga = new Hobby(_Yoga);
    public static final Hobby Fighting = new Hobby(_Fighting);
    public static final Hobby Riding = new Hobby(_Riding);
    public static final Hobby Pingpong = new Hobby(_Pingpong);
    public static final Hobby Badminton = new Hobby(_Badminton);
    public static final Hobby Tennis = new Hobby(_Tennis);
    public static final Hobby Basketball = new Hobby(_Basketball);
    public static final Hobby Football = new Hobby(_Football);
    public static final Hobby Swimming = new Hobby(_Swimming);
    public static final Hobby Golf = new Hobby(_Golf);
    public static final Hobby Bowling = new Hobby(_Bowling);
    public static final Hobby PC = new Hobby(_PC);
    public static final Hobby HandiCraftArt = new Hobby(_HandiCraftArt);
    public static final Hobby antique = new Hobby(_antique);
    public static final Hobby AlcoholandTobacco = new Hobby(_AlcoholandTobacco);
    public static final Hobby Tea = new Hobby(_Tea);
    public static final Hobby Cooking = new Hobby(_Cooking);
    public static final Hobby Travel = new Hobby(_Travel);
    public static final Hobby Finance = new Hobby(_Finance);
    public static final Hobby Digital = new Hobby(_Digital);
    public static final Hobby Car = new Hobby(_Car);
    public static final Hobby Cosmetology = new Hobby(_Cosmetology);
    public static final Hobby Chess = new Hobby(_Chess);
    public static final Hobby Shopping = new Hobby(_Shopping);
    public static final Hobby Luxury = new Hobby(_Luxury);
    public static final Hobby Others = new Hobby(_Others);
    public java.lang.String getValue() { return _value_;}
    public static Hobby fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        Hobby enumeration = (Hobby)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static Hobby fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Hobby.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/FX.Membership.DataContract", "hobby"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
