package com.zhike.blogwebapi;

import com.zhike.blogmanager.Shape.Shape;
import com.zhike.blogmanager.Shape.ShapeFactory;
import com.zhike.blogwebapi.Models.Animal;
import com.zhike.blogwebapi.Models.Elephant;
import com.zhike.blogwebapi.Models.Fridge;
import com.zhike.blogwebapi.Models.Lion;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogWebapiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void Test()
	{
		//实例化一头大象
//		Elephant elephant=new Elephant();
//
//		//实体化一个冰箱
//		Fridge fridge=new Fridge();
//		fridge.openTheDoor();
//		fridge.store(elephant);
//		fridge.closeTheDoor();

		//实例化一头狮子
		Animal lion=new Lion();
		Animal elephant=new Elephant();

		//实体化一个冰箱
		Fridge fridge=new Fridge();
        fridge.store(lion);
		lion.cry();
		fridge.closeTheDoor();

		fridge.store(elephant);
		elephant.cry();
		fridge.closeTheDoor();

	}

//	@Test
//	void testReflectFactory()
//	{
//		/**
//		 * get circle instance
//		 * */
//		Shape shapeCircle = ShapeFactory
//				.getInstance("com.zhike.blogmanager.Shape.Circle");
//		shapeCircle.draw();
//
//		/**
//		 * get rectangle instance
//		 * */
//		Shape shapeRectangle = ShapeFactory
//				.getInstance("com.zhike.blogmanager.Shape.Rectangle");
//		shapeRectangle.draw();
//
//		/**
//		 * get square instance
//		 * */
//		Shape shapeSquare = ShapeFactory
//				.getInstance("com.zhike.blogmanager.Shape.Square");
//		shapeSquare.draw();
//	}
}
