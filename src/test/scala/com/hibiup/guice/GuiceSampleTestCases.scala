package com.hibiup.guice

import org.scalatest.FlatSpec


class GuiceSampleTestCases extends FlatSpec {
  "A basic Guice DI" should "" in {
    import com.hibiup.guice.sample_1.Sample_1
    Sample_1()
  }

  "A Construct Guice DI" should "" in {
    import com.hibiup.guice.sample_2._
    Sample_2_construct_inject()
  }
}
