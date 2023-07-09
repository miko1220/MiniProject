package kr.demo.first.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	private int userIdx;
	private String userEmail;
	private String userName;
	private String userPassword;
	private String signUpDate;
}
