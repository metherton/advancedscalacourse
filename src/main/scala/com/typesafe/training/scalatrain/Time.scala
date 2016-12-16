/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import play.api.libs.json.{JsValue, Json}

import scala.annotation.tailrec
import scala.util.Try
import scala.collection.immutable.Seq

object Time {

  def fromMinutes(minutes: Int): Time =
    Time(minutes / 60, minutes % 60)

  def fromJson(json: JsValue): Option[Time] = {
    val tryTime = for {
      hours <- Try((json \ "hours").as[Int])
      minutes <- Try((json \ "minutes").as[Int]).recover({ case _: Exception => 0 })
    } yield Time(hours, minutes)
    tryTime.toOption
  }

//  @tailrec
//  def isIncreasing(times: Seq[Time]): Boolean =
//    times match {
//      case fst +: (tail @ snd +: _) => fst < snd && isIncreasing(tail)
//      case _ => true
//    }

  @tailrec
  def isIncreasing(times: Seq[Time]): Boolean = times match {
    case x1 +: (xs @ x2 +: _) => (x1 < x2) && isIncreasing(xs)
    case _ => true
  }

//    def isIncreasing(times: Seq[Time]): Boolean = {
//      def loop()
//    }

}

case class Time(hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {
  require(hours >= 0 && hours <= 23, "hours must be within 0 and 23")
  require(minutes >= 0 && minutes <= 59, "minutes must be within 0 and 59")

  val asMinutes: Int =
    hours * 60 + minutes

  override lazy val toString: String =
    f"$hours%02d:$minutes%02d"

  def minus(that: Time): Int =
    this.asMinutes - that.asMinutes

  def -(that: Time): Int =
    minus(that)

  // TODO This "pollutes" the API; in the Advanced Scala course we learn a better solution
  def toJson: JsValue =
    Json.obj("hours" -> hours, "minutes" -> minutes)

  override def compare(that: Time): Int =
    this - that
}
