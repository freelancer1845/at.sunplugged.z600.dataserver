package at.sunplugged.z600.dataserver.z600dataserver.repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.sunplugged.z600.dataserver.z600dataserver.model.DataPoint;
import at.sunplugged.z600.dataserver.z600dataserver.model.DataPoint.SessionPK;
import at.sunplugged.z600.dataserver.z600dataserver.model.Session;

public interface DataPointsRepository extends JpaRepository<DataPoint, SessionPK> {

	@Query("SELECT p from DataPoint p WHERE p.sessionPK.sessionId = :id")
	public List<DataPoint> findBySessionId(@Param("id") Long sessionId);

	@Query("SELECT p from DataPoint p WHERE p.sessionPK.sessionId = :id")
	public Stream<DataPoint> streamBySessionid(@Param("id") Long sessionId);

	public List<DataPoint.DatapointSessionData> findAllProjectedBy();

	@Query("SELECT new at.sunplugged.z600.dataserver.z600dataserver.model.Session(p.sessionPK.sessionId,MIN(p.time),MAX(p.time)) FROM DataPoint p WHERE p.sessionPK.sessionId = :sessionId")
	public Session getSessionBySessionId(Long sessionId);

	@Query("SELECT new at.sunplugged.z600.dataserver.z600dataserver.model.Session(p.sessionPK.sessionId,MIN(p.time),MAX(p.time)) FROM DataPoint p GROUP BY p.sessionPK.sessionId")
	public Collection<Session> getSessions();

	@Query("SELECT MAX(p.sessionPK.sessionId) from DataPoint p")
	public Long maxSession();

	@Query("SELECT MAX(p.sessionPK.dataPoint) from DataPoint p WHERE p.sessionPK.sessionId = :id")
	public Long maxDataPoint(@Param("id") Long sessionId);
}
