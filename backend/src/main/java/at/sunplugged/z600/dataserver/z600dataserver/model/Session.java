package at.sunplugged.z600.dataserver.z600dataserver.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Session {

	private Long sessionId;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

}
