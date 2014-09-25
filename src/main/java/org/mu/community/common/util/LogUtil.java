package org.mu.community.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mu.community.code.entity.NCSALog;
import org.mu.community.code.entity.RequestData;

public class LogUtil {

	private static final String NCSALOGPATTERN = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

	private static final SimpleDateFormat NCSADATEFORMAT = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss ZZZ");
	
	public static NCSALog parseNCSALog(String logString) {
		NCSALog log = new NCSALog();
		RequestData requestData = new RequestData();
		log.setRequest(requestData);

		Pattern pattern = Pattern.compile(NCSALOGPATTERN);
		Matcher matcher = pattern.matcher(logString);
		log.setRemotehost(matcher.group(1));
		if (matcher.group(2).equals("-")) {
			log.setIdent("");
		} else {
			log.setIdent(matcher.group(2));
		}
		if (matcher.group(3).equals("-")) {
			log.setAuthuser("");
		} else {
			log.setAuthuser(matcher.group(3));
		}
		try {
			log.setDate(NCSADATEFORMAT.parse(matcher.group(4)));
		} catch (ParseException e) {
		}
		String[] requestDataArray = matcher.group(5).split(" ");
		requestData.setMethod(requestDataArray[0]);
		requestData.setResource(requestDataArray[1]);
		requestData.setProtocol(requestDataArray[2]);
		log.setStatus(Integer.parseInt(matcher.group(6)));
		log.setBytes(Long.parseLong(matcher.group(7)));
		if (matcher.group(8).equals("-")) {
			log.setReferrer("");
		} else {
			log.setReferrer(matcher.group(8));
		}
		log.setAgent(matcher.group(9));
		return log;
	}

	public static NCSALog generateLog(HttpServletRequest request, HttpServletResponse response) {
		NCSALog log = new NCSALog();
		RequestData requestData = new RequestData();
		log.setRequest(requestData);
		
		log.setRemotehost(request.getHeader("host"));
		log.setAgent(request.getHeader("user-agent"));
		log.setIdent("-");
		String user = request.getRemoteUser();
		log.setAuthuser((user == null) ? "-" : user);
		log.setDate(new Date());
		requestData.setMethod(request.getMethod());
		requestData.setResource(request.getRequestURI());
		requestData.setProtocol(request.getProtocol());
		log.setStatus(response.getStatus());
		log.setBytes(0);
		return log;
	}

	public static String getLogString(HttpServletRequest request, HttpServletResponse response, 
			int responseLength) {
		StringBuffer buffer = new StringBuffer(160);

		buffer.append(request.getRemoteAddr());
		buffer.append(" - ");
		String user = request.getRemoteUser();
		buffer.append((user == null) ? "-" : user);
		buffer.append(" [");
		buffer.append(NCSADATEFORMAT.format(request));
		buffer.append("] \"");
		buffer.append(request.getMethod());
		buffer.append(' ');
		buffer.append(request.getRequestURI());
		buffer.append(' ');
		buffer.append(request.getProtocol());
		buffer.append("\" ");
		int status = response.getStatus();
		buffer.append((char) ('0' + ((status / 100) % 10)));
		buffer.append((char) ('0' + ((status / 10) % 10)));
		buffer.append((char) ('0' + (status % 10)));
		if (responseLength >= 0) {
			buffer.append(' ');
			if (responseLength > 99999) {
				buffer.append(Integer.toString(responseLength));
			} else {
				if (responseLength > 9999)
					buffer.append((char) ('0' + ((responseLength / 10000) % 10)));
				if (responseLength > 999)
					buffer.append((char) ('0' + ((responseLength / 1000) % 10)));
				if (responseLength > 99)
					buffer.append((char) ('0' + ((responseLength / 100) % 10)));
				if (responseLength > 9)
					buffer.append((char) ('0' + ((responseLength / 10) % 10)));
				buffer.append((char) ('0' + (responseLength % 10)));
			}
			buffer.append(' ');
		} else {
			buffer.append(" - ");
		}
		return buffer.toString(); 
	}

}
