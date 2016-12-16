package com.typesafe.training.scalatrain

/**
  * Created by martin on 16/12/16.
  */
object CustomColl {

  def map[A, B](as: List[A])(f: A => B): List[B] =
    as.foldLeft(List.empty[B])((bs, a) => bs :+ f(a))


}
