package at.sunplugged.z600.dataserver.z600dataserver.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.sunplugged.z600.dataserver.z600dataserver.model.DataPoint;
import at.sunplugged.z600.dataserver.z600dataserver.model.DataPoint.SessionPK;
import at.sunplugged.z600.dataserver.z600dataserver.model.Session;
import at.sunplugged.z600.dataserver.z600dataserver.repository.DataPointsRepository;

@Service
public class DatasessionsService {

	private DataPointsRepository repository;

	@Autowired
	public DatasessionsService(DataPointsRepository repository) {
		this.repository = repository;

		// createDummySessions();
	}

	private void createDummySessions() {
		long k = getNextSessionId();

		for (long i = k; i < k + 1; i++) {
			List<DataPoint> points = new ArrayList<>(18000);
			for (long j = 1; j < 1800; j++) {
				DataPoint point = new DataPoint();
				SessionPK pk = new SessionPK();
				pk.setSessionId(i);
				pk.setDataPoint(j);
				point.setSessionPK(pk);

				point.setTime(LocalDateTime.now().plusSeconds(j).plusDays(i));

				point.setConveyorEngineLeftMaximum(0.0);
				point.setConveyorEngineRightMaximum(0.0);
				point.setConveyorMode("STOP");
				point.setConveyorPositionCombined(0.0);
				point.setConveyorEngineLeftMaximum(0.0);
				point.setConveyorEngineRightMaximum(0.0);
				point.setConveyorPositionLeft(0.0);
				point.setConveyorPositionRight(0.0);
				point.setConveyorSpeedCombined(0.0);
				point.setConveyorSpeedLeft(0.0);
				point.setConveyorSpeedRight(0.0);
				point.setConveyorSpeedSetpoint(0.0);
				point.setPinnacleCurrent(0.0);
				point.setPinnacleVoltage(0.0);
				point.setPinnaclePowerSetpoint(0.0);
				point.setPinnacleVoltage(0.0);
				point.setSsvOneVoltage(0.0);
				point.setSsvTwoVoltage(0.0);
				point.setSsvOneCurrent(0.0);
				point.setSsvTwoCurrent(0.0);
				point.setSsvTwoPower(0.0);
				point.setSsvOnePower(0.0);
				points.add(point);
			}
			repository.saveAll(points);
			repository.flush();
		}

	}

	public List<Session> getSessions() {
		List<Session> sessions = new ArrayList<>(repository.getSessions());
		Collections.reverse(sessions);

		return sessions;
		// List<Session> list = new ArrayList<>();
		// list.add(repository.getSessionBySessionId((long) 1));
		// return list;
		//
		// long start = System.currentTimeMillis();
		//
		// List<DataPoint.DatapointSessionData> data = repository.findAllProjectedBy();
		// System.out.println("Data fetch needed: " + (System.currentTimeMillis() -
		// start));
		//
		// final List<Session> sessions = new ArrayList<>();
		// start = System.currentTimeMillis();
		// Session currentSession = new Session();
		// currentSession.setSessionId(-1L);
		//
		// for (int i = 0; i < data.size(); i++) {
		// DataPoint.DatapointSessionData dataPoint = data.get(i);
		// if (dataPoint.getSessionPK().getSessionId() != currentSession.getSessionId())
		// {
		// if (i > 0) {
		// currentSession.setEndTime(data.get(i - 1).getTime());
		// }
		// currentSession = new Session();
		// currentSession.setSessionId(dataPoint.getSessionPK().getSessionId());
		// currentSession.setStartTime(dataPoint.getTime());
		// sessions.add(currentSession);
		// }
		//
		// }
		//
		// System.out.println("Data organizing needed: " + (System.currentTimeMillis() -
		// start));

		//
		// start = System.currentTimeMillis();
		// Map<Long, List<DataPoint.DatapointSessionData>> sessionsMap = data.stream()
		// .collect(Collectors.groupingBy(point ->
		// point.getSessionPK().getSessionId()));
		// System.out.println("Data grouping needed: " + (System.currentTimeMillis() -
		// start));
		//
		// start = System.currentTimeMillis();
		// sessionsMap.forEach((id, points) -> {
		// Session session = new Session();
		// session.setSessionId(id);
		//
		// LocalDateTime startTime = points.get(0).getTime();
		//
		// session.setStartTime(startTime);
		// LocalDateTime endTime = points.get(points.size() - 1).getTime();
		//
		// session.setEndTime(endTime);
		// sessions.add(session);
		// });
		//
		// System.out.println("Calculating start end time needed: " +
		// (System.currentTimeMillis() - start));

		// return sessions;
	}

	public List<DataPoint> getDataPointsFromSessionId(Long id) {
		return repository.findBySessionId(id);
	}

	public Stream<DataPoint> getDataPointsStreamFromSessionId(Long id) {
		return repository.streamBySessionid(id);
	}

	public Long getNextSessionId() {
		return repository.maxSession() + 1;
	}

	public Long getNextDataPoint(Long sessionId) {
		Long maxDataPoint = repository.maxDataPoint(sessionId);
		if (maxDataPoint == null) {
			return 1L;
		} else {
			return maxDataPoint + 1;
		}
	}

	public void addDataPoint(DataPoint point) {
		repository.save(point);
	}

}
