package com.greencatsoft.angularjs.core

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.UndefOr.any2undefOrA

import com.greencatsoft.angularjs.{ PageController, TitledPageController, injectable }

@injectable("$routeProvider")
trait RouteProvider extends js.Object {

  def when(path: String, route: Route): this.type = js.native

  def otherwise(route: Route): this.type = js.native
}

trait Route extends js.Object {

  var title: UndefOr[String] = js.native

  var templateUrl: UndefOr[String] = js.native

  var controller: UndefOr[String] = js.native

  var redirectTo: UndefOr[String] = js.native
}

object Route {

  def apply(templateUrl: String, title: String = null): Route = {
    require(templateUrl != null, "Missing argument 'templateUrl'.")
    require(title != null, "Missing argument 'title'.")

    val route = new js.Object().asInstanceOf[Route]

    Option(title).foreach(route.title = _)

    route.templateUrl = templateUrl
    route
  }

  def apply(controller: PageController): Route = {
    require(controller != null, "Missing argument 'controller'.")

    val route = new js.Object().asInstanceOf[Route]

    route.templateUrl = controller.templateUrl
    route.controller = controller.name

    controller match {
      case titled: TitledPageController => route.title = titled.title
      case _ =>
    }

    route
  }

  def redirectTo(url: String): Route = {
    require(url != null, "Missing argument 'url'.")

    val route = new js.Object().asInstanceOf[Route]

    route.redirectTo = url
    route
  }
}

trait RouteInfo extends js.Object {

  var $$route: Route = js.native

  var loadedTemplateUrl: String = js.native

  var params: js.Array[js.Any] = js.native

  var pathParams: js.Array[js.Any] = js.native

  var scope: Scope = js.native
}
