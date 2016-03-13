package ex3

import java.security.MessageDigest

/**
  * Created by wiktor on 13/03/16.
  */
object CheckSumHelper {
  val MD5_LEN: Int = 16
  private val md: MessageDigest = MessageDigest.getInstance("MD5")

  def calculateCheckSum(buffer: Array[Byte], len: Integer): Array[Byte] = {
    md.update(buffer, 0, len)
    md.digest()
  }

  def checkIfCheckSumMatch(buffer: Array[Byte], len: Integer, checkSum: Array[Byte]): Boolean = {
    val calculatedCheckSum = calculateCheckSum(buffer, len)
    if (!calculatedCheckSum.corresponds(checkSum){_ == _}) {
      return false
    }
    true
  }
}
