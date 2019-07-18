package models

import play.api.data._
import play.api.data.Forms._

import scala.collection.mutable.ArrayBuffer

case class Cats(name: String,age: Int)

object Cats {
  val createCatsForm = Form(
    mapping (
    "name" -> nonEmptyText,
    "age" -> number (min = 0, max = 100)
    )(Cats.apply)(Cats.unapply)
  )
  val catsList = new ArrayBuffer[Cats]
}
