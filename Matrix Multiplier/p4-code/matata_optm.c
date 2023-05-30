// optimized versions of matrix A^T*A operation
#include "matvec.h"
#include <string.h>

int matata_VER1(matrix_t mat, matrix_t ans) {
  matrix_t tra;
  matrix_init(&tra, mat.rows, mat.cols);

  for(int i=0; i<mat.rows; i++){                // form transpose mat
    for(int j=0; j<mat.cols; j++){
      int mij = MGET(mat, i, j);
      MSET(tra, j, i, mij);
      MSET(ans,i,j,0);
    }
  }
  for(int k = 0; k<mat.rows;k++) {
  for(int i = 0; i<mat.rows;i++) {
  for(int j = 0; j<mat.cols;j++) {
  MSET(ans,i,j,MGET(ans,i,j)+(MGET(mat,k,j)*MGET(tra,i,k)));
  }
  }
  }
  matrix_free_data(&tra);                       // de-allocate transpose mat

  return 0;                                     // return success
}
int matata_VER2(matrix_t mat, matrix_t ans) {
  int n = mat.rows;
  memset(ans.data, 0, sizeof(int) * n*n);
  for(int k = 0; k<n;k++) {
  for(int i = 0; i<n;i++) {
    int x = MGET(mat,k,i);
  for(int j = 0; j<n;j++) {
      MSET(ans,i,j,MGET(ans,i,j)+(MGET(mat,k,j)*x));
  }
  }
  }

  return 0;                                     
}

int matata_VER3(matrix_t mat, matrix_t ans) {
  int n = mat.rows;
  memset(ans.data, 0, sizeof(int) * n*n);
  for(int k = 0; k<n;k++) {
  for(int i = 0; i<n;i++) {
    int x = MGET(mat,k,i);
  for(int j = 0; j<n-1;j+=2) {
      MSET(ans,i,j,MGET(ans,i,j)+(MGET(mat,k,j)*x));
      MSET(ans,i,j+1,MGET(ans,i,j+1)+(MGET(mat,k,j+1)*x));
      /* MSET(ans,i,j+5,MGET(ans,i,j+5)+(MGET(mat,k,j+5)*x)); */
  }
  if(n%2!=0) {
    MSET(ans,i,n-1,MGET(ans,i,n-1)+(MGET(mat,k,n-1)*x));
  }
  }
  }

  return 0;                                     
}

int matata_VER4(matrix_t mat, matrix_t ans) {
  int n = mat.rows;
  memset(ans.data, 0, sizeof(int) * n*n);
  for(int k = 0; k<n;k++) {
  for(int i = 0; i<n;i++) {
    int x = MGET(mat,k,i);
  int div = n/4;
  for(int j = 0; j<div*4;j+=4) {
      MSET(ans,i,j,MGET(ans,i,j)+(MGET(mat,k,j)*x));
      MSET(ans,i,j+1,MGET(ans,i,j+1)+(MGET(mat,k,j+1)*x));
      MSET(ans,i,j+2,MGET(ans,i,j+2)+(MGET(mat,k,j+2)*x));
      MSET(ans,i,j+3,MGET(ans,i,j+3)+(MGET(mat,k,j+3)*x));
      /* MSET(ans,i,j+5,MGET(ans,i,j+5)+(MGET(mat,k,j+5)*x)); */
  }
  for(int j = div*4; j<n;j++) {
      MSET(ans,i,j,MGET(ans,i,j)+(MGET(mat,k,j)*x));
  }
  }
  }

  return 0;                                     
}

int matata_OPTM(matrix_t mat, matrix_t ans) {
  if(mat.rows != mat.cols ||                    // must be a square matrix for A^T*A
     mat.rows != ans.rows ||
     mat.cols != ans.cols)
  {
    printf("matata_OPTM: dimension mismatch\n");
    return 1;
  }

  // Call to some version of optimized code
  // return matata_VER1(mat, ans);
  // return matata_VER2(mat, ans);
  // return matata_VER3(mat, ans);
  return matata_VER4(mat, ans);
}
