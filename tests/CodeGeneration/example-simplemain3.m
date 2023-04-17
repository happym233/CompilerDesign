prt0
          sw -36(r14), r15
          % processing line58
          % processing write statement
          % processing line58
          % processing line58
          % processing line58
          % processing line58
          % processing: tmpVar3 := 3
          addi r1,r0,3
          sw -20(r14),r1
          % processing line58
          addi r5, r14, 0
          % processing: tmpVar5 := a
          lw r1,-4(r5)
          addi r5, r1, 0 
          % processing: tmpVar5 := a
          lw r1,0(r5)
          sw -28(r14), r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing: tmpVar2 := tmpVar2 * tmpVar5
          lw r1,-16(r14)
          lw r2,-28(r14)
          mul r1,r1,r2
          sw -16(r14),r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-12(r14)
          sw -8(r14), r1
          lw r1, -8(r14)
          addi r14, r14, -40
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -40
          addi r1, r0, charLineSep
          addi r14, r14, -40
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -40
          lw r15, -36(r14)
          lw r14, -32(r14)
          jr r15
entry
          addi r14, r0, topaddr
          % processing pointer
          addi r1, r0, -36
          add r1, r1, r14
          sw -4(r14), r1
          % processing pointer
          addi r1, r0, -48
          add r1, r1, r14
          sw -8(r14), r1
          % processing line65
          % processing line65
          % processing line65
          % processing line65
          % processing: tmpVar9 := 123
          addi r1,r0,123
          sw -24(r14),r1
          % processing: tmpVar8 := tmpVar9
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar7 := tmpVar8
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing: tmpVar6 := tmpVar7
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-12(r5)
          lw r4, -4(r5)
          add r5, r0, r4
          sw 0(r5), r6
          % processing line66
          addi r5, r14, 0
          lw r4, -4(r5)
          add r5, r0, r4
          sw -136(r14), r5
          % processing jump
          sw -164(r14), r14
          addi r14, r14, -132
          jl r15, prt0
          lw r1, -4(r14)
          lw r2, -8(r14)
hlt

floatAdd
          lw r1, -4(r14)
          lw r2, -8(r14)
          lw r3, -12(r14)
          lw r4, -16(r14)
gowhileFAdd
          cne r5, r2, r4
          bz r5, endwhileFAdd
          clt r6, r2, r4
          bz r6, elseFAdd
          muli r3, r3, 10
          subi r4, r4, 1
          j endIfFAdd
elseFAdd
          muli r1, r1, 10
          subi r2, r2, 1
endIfFAdd
          j gowhileFAdd
endwhileFAdd
          add  r1, r1, r3
          sw   -4(r14), r1
          sw   -8(r14), r2
          jr   r15
floatMul
          lw r1,  -4(r14)
          lw r2,  -8(r14)
          lw r3,  -12(r14)
          lw r4,  -16(r14)
          mul r1, r1, r3
          add r2, r2, r4
          sw  -4(r14), r1
          sw  -8(r14), r2
          jr   r15
floatLeq
          lw r1,  -4(r14)
          lw r2,  -8(r14)
          lw r3,  -12(r14)
          lw r4,  -16(r14)
gowhileFLeq
          cne r5, r2, r4
          bz   r5, endwhileFLeq
          clt r6, r2, r4
          bz r6, elseFLeq
          muli r3, r3, 10
          subi r4, r4, 1
          j endIfFLeq
elseFLeq
          muli r1, r1, 10
          subi r2, r2, 1
          endIfFLeq
          j gowhileFLeq
endwhileFLeq 
          cle r1, r1, r3
          sw -4(r14), r1
          jr r15

buf       res 20
charE     db "e", 0
charLineSep    db "  ", 0
