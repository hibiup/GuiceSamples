package com.hibiup.guice

package Example_3_Module {

    import com.google.inject.Guice
    import com.google.inject.AbstractModule
    import com.google.inject.Provides
    import javax.inject.Inject

    /**
      * Guice 也可以通过 Module 功能来载入功能实例。例如以下定义一个名为 TextEditorModule 的模块，在模块内通过
      * @Provides 来声明一个实例：
      *
      * 1) 定义一个 Module
      * */
    class TextEditorModule extends AbstractModule {
        override protected def configure(): Unit = {
        }

        /** 2) 声明一个 SpellChecker 的实例 */
        @Provides def provideSpellChecker: SpellChecker = {
            val dbUrl = "jdbc:mysql://localhost:5326/emp"
            val user = "user"
            val timeout = 100
            val spellChecker = new SpellChecker(dbUrl, user, timeout) {
                override def checkSpelling(): Unit =  {
                    System.out.println("Inside checkSpelling.")
                    System.out.println(dbUrl)
                    System.out.println(user)
                    System.out.println(timeout)
                }
            }
            spellChecker
        }
    }

    /** 2-1) SpellChecker 实例的接口定义 */
    abstract class SpellChecker(dbUrl:String, user:String, timeout:Integer) {
        def checkSpelling()
    }

    object GuiceTester1 extends App {
        /** 3) 载入模块 */
        val injector = Guice.createInjector(new TextEditorModule)

        /** 4) 直接使用模块内定义的 Provide */
        val checker = injector.getInstance(classOf[SpellChecker])
        checker.checkSpelling()
    }


    /** 5) 或注入到一个第三方类中 */
    class TextEditor @Inject()(spellChecker: SpellChecker) {
        def makeSpellCheck {
            spellChecker.checkSpelling
        }
    }
    object GuiceTester2 extends App {
        val injector = Guice.createInjector(new TextEditorModule)   // 同样需要显示声明模块，因为模块中有实例化函数 @Providers

        /** 5-1) 使用这个第三方类中注入的接口 */
        val editer = injector.getInstance(classOf[TextEditor])
        editer.makeSpellCheck
    }


}
