package cz.koroptev.mcms.entities;

import java.util.Date;

import com.jirout.common.SingleIdObject;

public class Image implements SingleIdObject {

    private Integer id;

    private String name;
    private String extension;
    private Date dateInsert;

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getExtension() {
	return extension;
    }

    public void setExtension(String extension) {
	this.extension = extension;
    }

    public Date getDateInsert() {
	return dateInsert;
    }

    public void setDateInsert(Date dateInsert) {
	this.dateInsert = dateInsert;
    }

}
