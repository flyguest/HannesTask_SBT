import Main._
import Menu._
import Products._

import scala.util.Success

/**
  * Created by yuan on 04/10/2016.
  */

object MenuControl {

  def checkProduct() = {
    massage("Please enter a product name or enter 0 back to the main Menu")
    while (true) {
      val input = scala.io.StdIn.readLine()
      input match {
        case "" => massage("Come on Boss! Please enter something or enter 0 back to the main Menu")
        case "0" => mainTemplate
        case _ => checkProductByName(input)
      }
    }
  }

  def checkCategory(lengthOfCategoryList:Int) = {
    val input = scala.io.StdIn.readLine()
    input match {
      case "0" => mainTemplate
      case _ => {
        scala.util.Try (input.toInt) match {
          case Success(_) => {
            if (input.toInt <= lengthOfCategoryList-1) {
              massage(s"Products in ${getCategory(input.toInt - 1)} are:")
              println("Name   ||    Price   ||   Category")
              for (product <- getProductsInACategory(getCategory(input.toInt - 1)).values){
                println(s"${product.name}   £${product.price}   ${product.category}")
              }
              massage("")
              massage("End of products list")
              massage("")
              massage("Listed as above")
              massage("Are there anything else I can do for you my boss?")
              categoryMenu()
            }else categoryMenu()
          }
          case _ => categoryMenu()
        }
      }
    }
  }

  def checkAllProducts() = {
    readInput match {
      case "0" => mainTemplate
      case "1" => {
        println("Name   ||    Price   ||   Category")
        for(product <- productsMap.toList.map(_._2).sortBy(_.name)){
          println(s"${product.name}   £${product.price}   ${product.category}")
        }
      }
      case "2" => {
        println("Name   ||    Price   ||   Category")
        for(product <- productsMap.toList.map(_._2).sortBy(_.price)){
          println(s"${product.name}   £${product.price}   ${product.category}")
        }
      }
      case "3" => {
        println("Name   ||    Price   ||   Category")
        for(product <- productsMap.toList.map(_._2).sortBy(_.category)){
          println(s"${product.name}   £${product.price}   ${product.category}")
        }
      }
      case _ => {
        massage("Please enter something valid")
      }
    }

    massage("")
    massage("End of products list")
    massage("")
    mainMenu()
  }

  def checkProductByName(name:String): Any = {
    getProductByName(name) match {
      case Some(v) => {
        massage("The price for " + name + " is " + v.price)
        massage("Please enter a product name or enter 0 back to the main Menu")
      }
      case None => massage(name + " is a Invalid product's name, Please try again or enter 0 back to the main Menu")
    }
  }
}
