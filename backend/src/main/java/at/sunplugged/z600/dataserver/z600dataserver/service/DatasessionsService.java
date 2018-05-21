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
  }

  public List<DataPoint> getDataPointsFromSessionId(Long id) {
    return repository.findBySessionId(id);
  }

  public Stream<DataPoint> getDataPointsStreamFromSessionId(Long id) {
    return repository.streamBySessionid(id);
  }

  public Long getNextSessionId() {
    Long maxSession = repository.maxSession();
    if (maxSession != null) {
      return maxSession + 1;
    } else {
      return 1L;
    }
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

  public void deleteSession(Long sessionId) {
    repository.removeBySessionPK_SessionId(sessionId).stream().forEach(
        point -> System.out.println(point.getSessionPK().getSessionId()));
  }

}
