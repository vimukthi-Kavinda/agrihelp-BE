package kln.project.marketservice.model.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataTableBean {
    private long count; //as react count too
    private long pagecount;
    private List<Object> list;

    private boolean reacted;
    private String userreact;
    private String positiveCount;
    private String negativeCount;
}
