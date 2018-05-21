package at.sunplugged.z600.dataserver.z600dataserver.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import at.sunplugged.z600.dataserver.z600dataserver.model.DataPoint;
import at.sunplugged.z600.dataserver.z600dataserver.model.Session;
import at.sunplugged.z600.dataserver.z600dataserver.service.DatasessionsService;

@CrossOrigin(origins = CrossOriginConfig.ALLOWED_CROSS_ORIGIN, maxAge = CrossOriginConfig.MAX_AGE)
@RestController
@RequestMapping({"/sessions/api"})
public class DatasessionsController {

  private static final String[] columnHeaders = {"DataPoint", "DateTime", "P_Cryo_One",
      "P_Cryo_Two", "P_Chamber", "P_Turbo_Pump", "Gas_Flow_SCCM", "PinnaclePower",
      "Pinnacle_Power_Setpoint", "Pinnacle_Current", "Pinnacle_Voltage", "SSV_One_Power",
      "SSV_One_Power_Setpoint", "SSV_One_Current", "SSV_One_Voltage", "SSV_Two_Power",
      "SSV_Two_Power_Setpoint", "SSV_Two_Current", "SSV_Two_Voltage", "Conveyor_Speed_Combined",
      "Conveyor_Speed_Left", "Conveyor_Speed_Right", "Conveyor_Speed_Setpoint",
      "Conveyor_Engine_Left_Maximum", "Conveyor_Engine_Right_Maximum", "Conveyor_Position_Left",
      "Conveyor_Position_Right", "Conveyor_Position_Combined", "Conveyor_Mode",
      "SRM_Channel_2_Left", "SRM_Channel_3_Right"};

  private DatasessionsService dataSessionsService;

  @Autowired
  public DatasessionsController(DatasessionsService datasessionsService) {
    this.dataSessionsService = datasessionsService;
  }

  @GetMapping(path = {""})
  public List<Session> getSessions() {
    return dataSessionsService.getSessions();
  }

  @GetMapping(path = "/newSession")
  public Long getNewSession() {
    return dataSessionsService.getNextSessionId();
  }

  @PostMapping(path = "/delete/{id}")
  public void deleteSession(@PathVariable("id") Long id) {
    dataSessionsService.deleteSession(id);
  }

  @GetMapping(path = {"/{id}"})
  public List<DataPoint> getDataPointsFromSessionId(@PathVariable("id") Long id) {
    return dataSessionsService.getDataPointsFromSessionId(id);
  }

  @RequestMapping(path = "/{id}/download", method = RequestMethod.GET)
  @Transactional(readOnly = true)
  public void downloadSession(@PathVariable("id") Long id, HttpServletResponse response)
      throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException {

    response.addHeader("Content-disposition", "attachment");
    response.setContentType("text/csv");

    CSVWriter csvWriter =
        new CSVWriter(response.getWriter(), ';', CSVWriter.DEFAULT_QUOTE_CHARACTER,
            CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

    csvWriter.writeNext(columnHeaders);
    dataSessionsService.getDataPointsStreamFromSessionId(id).map(this::toCSVRow).forEach(
        csvWriter::writeNext);
    // List<DataPoint> points = getDataPointsFromSessionId(id);
    //
    // ByteArrayOutputStream os = new ByteArrayOutputStream();
    // StatefulBeanToCsv<DataPoint> beanToCsv = new
    // StatefulBeanToCsvBuilder<DataPoint>(response.getWriter())
    // .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
    // beanToCsv.write(points);

    response.flushBuffer();
    csvWriter.close();
  }

  private String[] toCSVRow(DataPoint point) {

    String[] array = new String[31];

    int idx = 0;

    array[idx++] = point.getSessionPK().getDataPoint().toString();
    array[idx++] = point.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    array[idx++] = Double.toString(point.getPressureCryoOne());
    array[idx++] = Double.toString(point.getPressureCryoTwo());
    array[idx++] = Double.toString(point.getPressureChamber());
    array[idx++] = Double.toString(point.getPressureTurboPump());
    array[idx++] = Double.toString(point.getCurrentGasFlowSccm());
    array[idx++] = Double.toString(point.getPinnaclePower());
    array[idx++] = Double.toString(point.getPinnaclePowerSetpoint());
    array[idx++] = Double.toString(point.getPinnacleCurrent());
    array[idx++] = Double.toString(point.getPinnacleVoltage());
    array[idx++] = Double.toString(point.getSsvOnePower());
    array[idx++] = Double.toString(point.getSsvOnePowerSetpoint());
    array[idx++] = Double.toString(point.getSsvOneCurrent());
    array[idx++] = Double.toString(point.getSsvOneVoltage());
    array[idx++] = Double.toString(point.getSsvTwoPower());
    array[idx++] = Double.toString(point.getSsvTwoPowerSetpoint());
    array[idx++] = Double.toString(point.getSsvTwoCurrent());
    array[idx++] = Double.toString(point.getSsvTwoVoltage());
    array[idx++] = Double.toString(point.getConveyorSpeedCombined());
    array[idx++] = Double.toString(point.getConveyorSpeedLeft());
    array[idx++] = Double.toString(point.getConveyorSpeedRight());
    array[idx++] = Double.toString(point.getConveyorSpeedSetpoint());
    array[idx++] = Double.toString(point.getConveyorEngineLeftMaximum());
    array[idx++] = Double.toString(point.getConveyorEngineRightMaximum());
    array[idx++] = Double.toString(point.getConveyorPositionLeft());
    array[idx++] = Double.toString(point.getConveyorPositionRight());
    array[idx++] = Double.toString(point.getConveyorPositionCombined());
    array[idx++] = point.getConveyorMode();
    array[idx++] = Double.toString(point.getSrmChannelTwoLeft());
    array[idx++] = Double.toString(point.getSrmChannelThreeRight());

    return array;

  }

  @GetMapping(path = {"/{id}/csv"})
  public @ResponseBody byte[] getSessionAsCSV(@PathVariable("id") Long id)
      throws CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
    List<DataPoint> points = getDataPointsFromSessionId(id);

    ByteArrayOutputStream os = new ByteArrayOutputStream();
    StatefulBeanToCsv<DataPoint> beanToCsv = new StatefulBeanToCsvBuilder<DataPoint>(
        new BufferedWriter(new OutputStreamWriter(os))).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                                                       .build();
    beanToCsv.write(points);

    return os.toByteArray();

  }

  @GetMapping(path = "/nextdatapoint/{id}")
  public Long getNextDatapoint(@PathVariable("id") Long id) {
    return dataSessionsService.getNextDataPoint(id);
  }

  @PutMapping(path = "/putdatapoint")
  public void putDataPoint(@RequestBody DataPoint point) {
    dataSessionsService.addDataPoint(point);
  }
}
