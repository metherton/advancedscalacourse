package com.typesafe.training.scalatrain

import scala.annotation.tailrec

/**
  * Created by martin on 16/12/16.
  */
object CustomColl {

//  def map[A, B](as: List[A])(f: A => B): List[B] =
//    as.foldLeft(List.empty[B])((bs, a) => bs :+ f(a))

//  def map[A, B](as: List[A])(f: A => B): List[B] = as match {
//    case Nil => Nil
//    case x :: xs => f(x) :: map(xs)(f)
//  }


  def map[A, B](as: List[A])(f: A => B): List[B] =  {
    @tailrec
    def loop(as: List[A], acc: List[B]): List[B] = as match {
      case Nil => acc
      case x :: xs => loop(xs, acc :+ f(x))
    }
    loop(as, List.empty[B])
  }
}
