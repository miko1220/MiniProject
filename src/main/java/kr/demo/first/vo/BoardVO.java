package kr.demo.first.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	private String view_cnt;
	private String create_date;
	private String update_date;
}
