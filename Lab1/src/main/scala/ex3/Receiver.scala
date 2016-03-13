package ex3

import java.io.{ByteArrayInputStream, DataInputStream}
import java.net.{DatagramPacket, InetAddress, MulticastSocket}

/**
  * Created by wiktor on 12/03/16.
  */
class Receiver(ip: String, port: Int, nickname: String) extends Runnable {

  override def run(): Unit = {
    val socket: MulticastSocket = new MulticastSocket(port)
    socket.joinGroup(InetAddress.getByName(ip))
    val receiveBuffer: Array[Byte] = new Array[Byte](socket.getReceiveBufferSize)
    val datagramPacket: DatagramPacket = new DatagramPacket(receiveBuffer, socket.getReceiveBufferSize)
    while (true) {
      println
      socket.receive(datagramPacket)
      val inputStream: DataInputStream = new DataInputStream(new ByteArrayInputStream(receiveBuffer))

      val nickBuilder = StringBuilder.newBuilder
      val nickLength: Int = inputStream.read()

      for (i <- 0 until nickLength) {
        nickBuilder.append(inputStream.read().toChar)
      }

      if (!nickBuilder.toString().equals(nickname)) {
        val builder = StringBuilder.newBuilder
        builder.append("[")
        builder.append(nickBuilder.toString())
        builder.append("] ")

        val messageLength: Int = inputStream.read()
        for (i <- 0 until messageLength) {
          builder.append(inputStream.read().toChar)
        }
        builder.append(" [")
        for (i <- 0 until 2) {
          builder.append(inputStream.read())
          builder.append(":")
        }
        builder.append(inputStream.read())
        builder.append("]")

        val checkSum: Array[Byte] = new Array[Byte](CheckSumHelper.MD5_LEN)
        inputStream.read(checkSum, 0, CheckSumHelper.MD5_LEN)
        if (CheckSumHelper.checkIfCheckSumMatch(receiveBuffer, nickLength + messageLength + 4, checkSum)) {
          println(builder.toString())
        } else {
          println(builder.toString() + " THIS MESSAGE MAY BE MALFORMED!")
        }
      }
    }
  }
}
