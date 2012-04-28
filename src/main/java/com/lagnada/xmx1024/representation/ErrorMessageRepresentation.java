package com.lagnada.xmx1024.representation;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.io.Serializable;
import java.util.List;

@JsonTypeName("errors")
//@JsonTypeInfo(use= JsonTypeInfo.Id.NAME, include= JsonTypeInfo.As.WRAPPER_OBJECT)
public class ErrorMessageRepresentation implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<ErrorResponse> errors;

    public ErrorMessageRepresentation() {
        super();
    }

    public void addError(ErrorResponse error) {
        errors.add(error);
    }

    @JsonProperty("count")
    public int getErrorCount() {

        return errors != null ? errors.size() : 0;
    }

    @JsonProperty("errors")
    public List<ErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorResponse> errorResponseList) {
        this.errors = errorResponseList;
    }

}
