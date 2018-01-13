package br.leonardodias.bigguardbot;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class FXMLDocumentController implements Initializable {

    private boolean detect = false;
    private boolean started = false;
    private boolean record = false;

    @FXML
    private Button start_btn, stop_btn;

    @FXML
    private ImageView currentFrame;

    //OpenCV variables
    private final Size minSize = new Size(30, 60);
    private final Size maxSize = new Size(70, 280);
    private VideoCapture capture;
    private Mat frame, grayFrame;
    private MatOfRect buffer;
    private MatOfByte bufferizado;
    private Image imageToShow;
    private VideoWriter videoWriter;
    private ScheduledExecutorService timer;

    @FXML
    public void startCamera(ActionEvent event) {
        startCommand();
    }

    @FXML
    private void stopCamera(ActionEvent event) {
        stopCommand();
    }

    public void startCommand() {
        started = true;
        //capture = new VideoCapture(0);
        capture = new VideoCapture("resources/walking.mp4");
        frame = new Mat();
        grayFrame = new Mat();
        buffer = new MatOfRect();
        bufferizado = new MatOfByte();
        final Size frameSize = new Size((int) capture.get(Videoio.CAP_PROP_FRAME_WIDTH),
                (int) capture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
        CascadeClassifier Detector = new CascadeClassifier("resources/peopleDetectionLBP.xml");
        final FourCC fourCC = new FourCC("X264");
        videoWriter = new VideoWriter("C:\\video.mp4", fourCC.toInt(),
                capture.get(Videoio.CAP_PROP_FPS), frameSize, true);
        Runnable frameGrabber = new Runnable() {
            @Override
            public void run() {
                while (started) {
                    if (capture.read(frame)) {
                        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);

                        if (detect) {
                            Detector.detectMultiScale(grayFrame, buffer, 1.1, 7, 0 | 1, minSize, maxSize);
                            for (Rect rect : buffer.toArray()) {
                                Imgproc.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2, 8, 0);
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, "RETANGULO");
                            }
                        }
                        Imgcodecs.imencode(".png", frame, bufferizado);
                        imageToShow = new Image(new ByteArrayInputStream(bufferizado.toArray()));
                        onFXThread(currentFrame.imageProperty(), imageToShow);                       
                        if (record) {
                            videoWriter.write(frame);
                        }
                        imageToShow = null;
                        buffer.release();
                        bufferizado.release();
                        grayFrame.release();

                    } else {
                        capture = new VideoCapture("resources/walking.mp4");
                    }
                }
            }
        };
        this.timer = Executors.newSingleThreadScheduledExecutor();
        this.timer.scheduleAtFixedRate(frameGrabber, 0, 30, TimeUnit.MILLISECONDS);
    }

    public <T> void onFXThread(final ObjectProperty<T> property, final T value) {
        Platform.runLater(() -> {
            property.set(value);
        });
    }

    public void stopCommand() {
        started = false;
        capture.release();
        buffer.release();
        frame.release();
        bufferizado.release();
        grayFrame.release();
        videoWriter.release();
        Platform.runLater(() -> {
            currentFrame.setImage(new Image("file:/C:/Users/myuser/Documents/NetBeansProjects/BigGuardBOT/resources/botguard.png"));
        });       
    }

    public void setDetect(boolean detect) {
        this.detect = detect;
    }

    public void photo(String filename) {
        Imgcodecs.imwrite("resources/imagens/" + filename + ".png", frame);
    }

    public void record(boolean status) {
        this.record = status;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

}
