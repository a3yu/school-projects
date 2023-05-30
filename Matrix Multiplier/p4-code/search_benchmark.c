#include <string.h>

#include <time.h>

#include <stdio.h>

#include <stdlib.h>

#include "search.h"

int main(int argc, char * argv[]) {
  int linearArray = 0;
  int linearList = 0;
  int binaryArray = 0;
  int binaryTree = 0;
  printf("%-15s", "LENGTH");
  printf("%-15s", "SEARCHES");
  if (argc > 4) {
    for (int i = 4; i < argc; i++) {
      if (strcmp(argv[i], "la") == 0) {
        linearArray = 1;

      }
      if (strcmp(argv[i], "ll") == 0) {
        linearList = 1;

      }
      if (strcmp(argv[i], "ba") == 0) {
        binaryArray = 1;

      }
      if (strcmp(argv[i], "bt") == 0) {
        binaryTree = 1;

      }
    }
  }
  if (linearArray) {
    printf("%-15s", "array");
  }
  if (linearList) {
    printf("%-15s", "list");
  }
  if (binaryArray) {
    printf("%-15s", "binary");
  }
  if (binaryTree) {
    printf("%-15s", "tree");
  }
  printf("\n");
  int start, end, rep;
  start = atoi(argv[1]);
  end = atoi(argv[2]);
  rep = atoi(argv[3]);
  int curSearch = 1;
  for (int i = 0; i < start; i++) {
    curSearch = curSearch * 2;
  }
  for (int p = start; p <= end; p++) {
    printf("%-15d", curSearch);
    printf("%-15d", 2 * curSearch * rep);
    if (linearArray) {
      double total = 0;
      for (int i = start; i <= end; i++) {
        int * searchArray = make_evens_array(curSearch);
        clock_t begin = clock();
        for (int j = 0; j < rep; j++) {
          for (int k = 0; k < 2 * curSearch; k++) {
            linear_array_search(searchArray, curSearch, k);
          }
        }
        clock_t end = clock();
        double endTime = ((double)(end - begin));
        total = total + endTime;
        free(searchArray);
      }
      printf("%-15E", total);
    }
    if (linearList) {
      double total = 0;
      for (int i = start; i <= end; i++) {
        list_t * searchList = make_evens_list(curSearch);
        clock_t begin = clock();
        for (int j = 0; j < rep; j++) {
          for (int k = 0; k < 2 * curSearch; k++) {
            linkedlist_search(searchList, curSearch, k);
          }
        }
        clock_t end = clock();
        double endTime = ((double)(end - begin));
        total = total + endTime;
        list_free(searchList);
      }
      printf("%-15E", total);
    }
    if (binaryArray) {
      double total = 0;
      for (int i = start; i <= end; i++) {
        int * searchArray = make_evens_array(curSearch);
        clock_t begin = clock();
        for (int j = 0; j < rep; j++) {
          for (int k = 0; k < 2 * curSearch; k++) {
            binary_array_search(searchArray, curSearch, k);
          }
        }
        clock_t end = clock();
        double endTime = ((double)(end - begin));
        total = total + endTime;
        free(searchArray);
      }
      printf("%-15E", total);
    }
    if (binaryTree) {
      double total = 0;
      for (int i = start; i <= end; i++) {
        bst_t * searchTree = make_evens_tree(curSearch);
        clock_t begin = clock();
        for (int j = 0; j < rep; j++) {
          for (int k = 0; k < 2 * curSearch; k++) {
            binary_tree_search(searchTree, curSearch, k);
          }
        }
        clock_t end = clock();
        double endTime = ((double)(end - begin));
        total = total + endTime;
        bst_free(searchTree);

      }
      printf("%-15E", total);
    }
    printf("\n");
    curSearch = curSearch * 2;
  }
  return 0;
}