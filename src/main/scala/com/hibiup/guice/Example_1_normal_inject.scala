package com.hibiup.guice.sample_1


/** 1) 引入 annotation */
import com.google.inject.Guice
import javax.inject._


/** 2) 定义一个注入单例 */
@Singleton class User {
  def say() = {
    println("Hello, GUICE!")
  }
}


/** 3）定义宿主 */
class UserService {
  // Inject 变量必须是 var！！
  @Inject var user:User = _

  def say(): Unit = {
    user.say()
  }
}


object Sample_1 {
  def apply(): Unit = {
    /** 4) 使用 */
    val injector = Guice.createInjector()
    val userService = injector.getInstance(classOf[UserService])
    userService.say()   // "Hello, GUICE!"
  }
}
