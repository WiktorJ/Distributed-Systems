package ex3

/**
  * Created by wiktor on 12/03/16.
  * packet format:
  * index 0 nick length (max 6)
  * index 1 - nick_length: nick (if shorter than 0)
  * index nick_length + 1: message length (max 20)
  * index nick_length + 2 - message_length: message
  * index message_length + 1 - message_length + 4: HMS
  * index message_length + 4 - message_length + 20: check sum
  */


object ChatEntity {

  def main(args: Array[String]) {
    val nickname = args(0)
    val ip = "224.0.0.1"
    val port = 8080
    if (nickname.length > 6 ) {
      println("Maximum length of nick is 6")
      return
    }

    val recThread = new Thread(new Receiver(ip, port, nickname))
    val sendThread = new Thread(new Sender(ip, port, nickname))
    recThread.start()
    sendThread.start()

  }

}

