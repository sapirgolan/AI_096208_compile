//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.09.08 at 11:14:57 PM EEST 
//


package com.technion.ai.dao;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}Name"/>
 *         &lt;element ref="{}isPositive" minOccurs="0"/>
 *         &lt;element ref="{}Parameter" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "isPositive",
    "parameter"
})
@XmlRootElement(name = "Predicat")
public class Predicat {

    @XmlElement(name = "Name", required = true)
    protected String name;
    protected Boolean isPositive;
    @XmlElement(name = "Parameter", required = true)
    protected List<Parameter> parameter;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the isPositive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsPositive() {
        return isPositive;
    }

    /**
     * Sets the value of the isPositive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsPositive(Boolean value) {
        this.isPositive = value;
    }

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Parameter }
     * 
     * 
     */
    public List<Parameter> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<Parameter>();
        }
        return this.parameter;
    }
    
    /** 
     * Call {@link #toString(boolean)} with boolean parameter set to false 
     * @see Predicat#toString(boolean)
     **/
    @Override
   	public String toString() {
    	return this.toString(false);
   	}
    
    /**
     * This method create the String representation of a predicat.
     * It can create it in two ways: with parameters's Type or without. The decision is based on the value of <code>withType</code> parameter
     * @param withType - boolean flag. If set to true the method generate String representation of a predicate with parameters's Type. 
     * @return Returns a string representation of the object
     */
    public String toString(boolean withType) {
    	StringBuilder builder = new StringBuilder();
       	builder.append("( ");
       	builder.append(this.name + " ");
       	for (Parameter param : parameter) {
       		String parameter = param.getName() + " ";
       		if (withType) {
       			parameter += "- " + param.getType() + " ";
       		}
       		builder.append( parameter );
		}
       	builder.append(")");
       	
   		return builder.toString();
    }

}
