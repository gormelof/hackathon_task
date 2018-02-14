
package net.gormelof.hackathontask.response.searchrecords;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
