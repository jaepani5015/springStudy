package study.webclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetData {
    private String postId;
    private String id;
    private String name;
    private String email;
    private String body;
}
