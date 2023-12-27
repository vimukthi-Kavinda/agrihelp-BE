package kln.project.customerservice.model.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataTableBean {
    private long count;
    private long pagecount;
    private List<Object> list;
}
