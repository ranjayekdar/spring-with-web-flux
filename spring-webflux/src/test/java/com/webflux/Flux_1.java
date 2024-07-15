package com.webflux;

import java.util.stream.IntStream;

import reactor.core.publisher.Flux;

public class Flux_1 {

	public static void main(String[] args) throws Exception {
		
		Flux<IntStream> just = Flux.just(IntStream.range(1, 700));
		just.subscribe(data->System.out.println(data.findFirst().getAsInt()));
	}
}
