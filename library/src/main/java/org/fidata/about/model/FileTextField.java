package org.fidata.about.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.ToString;
import org.fidata.utils.PathAbsolutizer;

@ToString(callSuper = true)
public class FileTextField extends PathField {
  @JsonCreator
  public FileTextField(
    @JacksonInject(value = PATH_ABSOLUTIZER, useInput = OptBoolean.FALSE) PathAbsolutizer pathAbsolutizer,
    String stringValue
  ) {
    super(pathAbsolutizer, stringValue);
  }
}
