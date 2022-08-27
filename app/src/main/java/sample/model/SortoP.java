package sample.model;

import javax.xml.datatype.XMLGregorianCalendar;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortoP {
  private int id;
  private String name;
  private XMLGregorianCalendar datetime;
}