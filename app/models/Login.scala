package models

import play.api.data._
import play.api.data.Forms._
import scala.collection.mutable.ArrayBuffer

case class Login(username: String, password: String)

object Login{
  val loginForm = Form (
    mapping(
      "name" -> nonEmptyText,
      "password" -> nonEmptyText
    )(Login.apply)(Login.unapply)
  )
  val validLogins = ArrayBuffer[Login](
    Login("admin", "admin"),
    Login("xXx_yoloSwaggins_xXx", "penguin")
  )
}
