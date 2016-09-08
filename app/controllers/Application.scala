package controllers

import play.api.libs.json._
import play.api.mvc._
import models.Book._

object Application extends Controller {

  def listEvents = Action {
    Ok(Json.toJson(books))
  }

  def saveEvent = Action(BodyParsers.parse.json) { request =>
    val b = request.body.validate[Book]
    b.fold(
      errors => {
        BadRequest(Json.obj("status" -> "OK", "message" -> JsError.toFlatJson(errors)))
      },
      book => {
        addBook(book)
        Ok(Json.obj("status" -> "OK"))
      }
    )
  }
}
