package ex3

import java.net.{DatagramPacket, DatagramSocket, InetAddress}

import org.joda.time.DateTime

import scala.util.{Failure, Success, Try}

/**
  * Created by wiktor on 13/03/16.
  */
class Sender(ip: String, port: Integer, nickname: String) extends Runnable {

  val MAX_DATA_LEN: Integer = 47
  val socket: DatagramSocket = new DatagramSocket()
  val sendDataBuffer: Array[Byte] = new Array[Byte](MAX_DATA_LEN)

  var messageLenIndex: Byte = 0

  def writeBuffer(message: String): Try[Int] = {
    if (message.length > 20 ) {
      return Failure(new IllegalArgumentException("Message length is grater than 20 characters"))
    }

    sendDataBuffer(messageLenIndex) = message.length.toByte
    for (i <- messageLenIndex + 1 to messageLenIndex + message.length) {
      sendDataBuffer(i) = message(i - messageLenIndex - 1).toByte
    }

    val hourIndex: Byte = (messageLenIndex + message.length + 1).toByte
    val time: DateTime = new DateTime()
    sendDataBuffer(hourIndex) = time.getHourOfDay.toByte
    sendDataBuffer(hourIndex + 1) = time.getMinuteOfHour.toByte
    sendDataBuffer(hourIndex + 2) = time.getSecondOfMinute.toByte

    val checkSum: Array[Byte] = CheckSumHelper.calculateCheckSum(sendDataBuffer, hourIndex + 2)
    val checkSumIndex: Int = hourIndex + 3
    for (i <- checkSumIndex until checkSumIndex + CheckSumHelper.MD5_LEN) {
      sendDataBuffer(i) = checkSum(i - checkSumIndex)
    }
    Success(checkSumIndex + CheckSumHelper.MD5_LEN)
  }

  override def run(): Unit = {
    sendDataBuffer(0) = nickname.length.toByte
    messageLenIndex = (nickname.length + 1).toByte

    for (i <- 1 to nickname.length) {
      sendDataBuffer(i) = nickname(i-1).toByte
    }

    while (true) {
      val message: String = io.Source.stdin.getLines().next()
      writeBuffer(message) match {
        case Success(len) => socket.send(new DatagramPacket(sendDataBuffer, len, InetAddress.getByName(ip), port))
        case Failure(ex) => println(ex)
      }
    }
  }
}
