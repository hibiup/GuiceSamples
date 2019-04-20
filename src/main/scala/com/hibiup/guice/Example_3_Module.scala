package com.hibiup.guice

package Example_3_Module {

    import com.google.inject.Guice
    import com.google.inject.AbstractModule
    import com.google.inject.Provides
    import javax.inject.{Inject, Named}

    /**
      * Guice 也可以通过 Module 功能来载入功能实例。例如以下定义一个名为 TextEditorModule 的模块，在模块内通过
      * @Provides 来声明一个实例：
      *
      * 1) 定义一个 Module
      * */
    class TextEditorModule extends AbstractModule {

        import com.google.inject.name.Names

        /** 设置参数 */
        override def configure(): Unit = {
            bindConstant.annotatedWith(Names.named("user name")).to("John")
            bindConstant.annotatedWith(Names.named("timeout")).to(200)
        }

        /**
          * 2) 声明一个 SpellChecker 的实例
          *
          * 可以给参数, 给参数定义一个 name，值可以通过 configure 显式定义，或通过其他 @Provides （隐式）注入。
          * */
        @Provides def provideSpellChecker(@Named("user name") id:String, @Named("timeout") timeout:Integer) : SpellChecker= {
            val spellChecker = new SpellChecker("jdbc:mysql://localhost:5326/emp", id+" Long", timeout) {
                override def checkSpelling(): Unit =  {
                    System.out.println("Inside checkSpelling.")
                    System.out.println(this.url)
                    System.out.println(this.user)
                    System.out.println(this.t)
                }
            }
            spellChecker
        }
    }

    /** 2-1) SpellChecker 实例的接口定义 */
    abstract class SpellChecker(val url:String, val user:String, val t:Integer) {
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
