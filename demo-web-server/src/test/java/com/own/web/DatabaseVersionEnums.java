package com.own.web;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据库版本
 * @author liuChang
 * @date 2023/8/30 11:26
 */

@Getter
@AllArgsConstructor
public enum DatabaseVersionEnums {

	/**
	 * mysql 版本
	 *
	 * @since 7.0.0.1
	 */

	EIGHT("MYSQL_8.X", "8.0"),
	FIVE("MYSQL_5.7.X", "5.7"),
	FIVE_LOW("MYSQL_5.6.X", "5.6"),
	;

	private final String code;

	private final String msg;

	// 提取主版本号和次版本号
	public static String extractMajorMinorVersion(String fullVersion) {
		Pattern pattern = Pattern.compile("(\\d+\\.\\d+)");
		Matcher matcher = pattern.matcher(fullVersion);

		if (matcher.find()) {
			String version = matcher.group(1);
			if (version.startsWith("8")){
				version = "8.0";
			}
			return getCodeByMsg(version);
		} else {
			// 如果无法匹配，默认返回原始版本号
			return fullVersion;
		}
	}

	public static String getCodeByMsg(String msg) {
		return Arrays.stream(values())
				.filter(versionEnum -> versionEnum.getMsg().equals(msg))
				.findFirst()
				.map(DatabaseVersionEnums::getCode)
				.orElse("");
	}
}
