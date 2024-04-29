#include <stdio.h>

#include <pthread.h>



#define NUM_THREADS 10

#define NUM_INCREMENTS 100000



int counter = 0;

pthread_mutex_t lock;



void* increment(void* arg) {

    for (int i = 0; i < NUM_INCREMENTS; i++) {

	pthread_mutex_lock(&lock);

        counter++;

	pthread_mutex_unlock(&lock);

    }

    pthread_exit(NULL);

}



int main() {

    pthread_t threads[NUM_THREADS];

    pthread_mutex_init(&lock, NULL);



    for (int i = 0; i < NUM_THREADS; i++) {

        pthread_create(&threads[i], NULL, increment, NULL);

    }



    for (int i = 0; i < NUM_THREADS; i++) {

        pthread_join(threads[i], NULL);

    }



    pthread_mutex_destroy(&lock);



    printf("Final counter value: %d\n", counter);



    return 0;

}
