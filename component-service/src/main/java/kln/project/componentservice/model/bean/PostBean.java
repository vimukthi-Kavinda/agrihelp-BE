package kln.project.componentservice.model.bean;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostBean {
    private String postType;
    private String postSubject;
    private String postDesc;

    private String cropcode;
}
