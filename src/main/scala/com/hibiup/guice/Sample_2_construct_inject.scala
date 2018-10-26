package com.hibiup.guice.sample_2


/** 1) 引入 annotation */
import com.google.inject.Guice
import javax.inject._


/** 2) 定义一个注入单例 */
@Singleton class User {
  def say() = {
    println("Hello, GUICE!")
  }
}


/** 3）定义宿主。构造注入可以避免使用 var */
class UserService @Inject()(user:User) {
  def say(): Unit = {
    user.say()
  }
}

object Sample_2_construct_inject {
  def apply() {
    /** 4) 使用 */
    val injector = Guice.createInjector()
    val userService = injector.getInstance(classOf[UserService])
    userService.say() // "Hello, GUICE!"
  }
}
