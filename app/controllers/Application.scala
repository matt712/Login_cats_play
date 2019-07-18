package controllers

import play.api._
import play.api.mvc._
import models.{Cats, Login}
import javax.inject.Inject
import play.api.i18n.{I18nSupport, MessagesApi}

class Application @Inject()(val messagesApi: MessagesApi, environment: play.api.Environment) extends Controller with I18nSupport {

  def index() = Action {
    Ok(views.html.index("Bad login"))
  }
  def listCats() = Action {implicit request =>
    Ok(views.html.listCats(Cats.catsList, Cats.createCatsForm))
  }
  def createCats() = Action {implicit  request =>
    val formValidationResult = Cats.createCatsForm.bindFromRequest
    formValidationResult.fold({ formWithErrors =>
      BadRequest(views.html.listCats(Cats.catsList, formWithErrors))
    }, { cat =>
      Cats.catsList.append(cat)
      Redirect(routes.Application.listCats())
  })
  }
  def vLogins() = Action {implicit  request =>
    Ok(views.html.login(Login.loginForm))
  }
  def login() = Action {implicit request =>
    val loginForm = Login.loginForm.bindFromRequest
    loginForm.fold({ formWithErrors =>
      BadRequest(views.html.login(formWithErrors))
    }, { login =>
      if(Login.validLogins.contains(login)) {
        Redirect("/cats")
      }
      else{
        Redirect(routes.Application.index())
      }
    })
  }
}
