prt0
          sw -44(r14), r15
          % processing line14
          % processing write statement
          % processing line14
          % processing line14
          % processing line14
          % processing line14
          addi r5, r14, 0
          % processing: tmpVar3 := a
          lw r1,-4(r5)
          addi r5, r1, 0 
          % processing: tmpVar3 := a
          lw r1,-8(r5)
          sw -20(r14), r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-12(r14)
          sw -8(r14), r1
          lw r1, -8(r14)
          addi r14, r14, -48
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -48
          addi r1, r0, charLineSep
          addi r14, r14, -48
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -48
          % processing line15
          % processing write statement
          % processing line15
          % processing line15
          % processing line15
          % processing line15
          addi r5, r14, 0
          % processing: tmpVar7 := b
          lw r1,-4(r5)
          addi r5, r1, 0 
          % processing: tmpVar7 := b
          lw r1,0(r5)
          sw -36(r14), r1
          % processing: tmpVar6 := tmpVar7
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing: tmpVar5 := tmpVar6
          lw r1,-32(r14)
          sw -28(r14), r1
          % processing: tmpVar4 := tmpVar5
          lw r1,-28(r14)
          sw -24(r14), r1
          lw r1, -24(r14)
          addi r14, r14, -48
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -48
          addi r1, r0, charLineSep
          addi r14, r14, -48
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -48
          lw r15, -44(r14)
          lw r14, -40(r14)
          jr r15
entry
          addi r14, r0, topaddr
          % processing pointer
          addi r1, r0, -48
          add r1, r1, r14
          sw -4(r14), r1
          % processing line21
          % processing line21
          % processing line21
          % processing line21
          % processing: tmpVar11 := 123
          addi r1,r0,123
          sw -20(r14),r1
          % processing: tmpVar10 := tmpVar11
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing: tmpVar9 := tmpVar10
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing: tmpVar8 := tmpVar9
          lw r1,-12(r14)
          sw -8(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-8(r5)
          lw r4, -4(r5)
          add r5, r0, r4
          sw -8(r5), r6
          % processing line22
          % processing line22
          % processing line22
          % processing line22
          % processing: tmpVar15 := 234
          addi r1,r0,234
          sw -36(r14),r1
          % processing: tmpVar14 := tmpVar15
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing: tmpVar13 := tmpVar14
          lw r1,-32(r14)
          sw -28(r14), r1
          % processing: tmpVar12 := tmpVar13
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-24(r5)
          lw r4, -4(r5)
          add r5, r0, r4
          sw 0(r5), r6
          % processing line23
          addi r5, r14, 0
          lw r4, -4(r5)
          add r5, r0, r4
          sw -64(r14), r5
          % processing jump
          sw -100(r14), r14
          addi r14, r14, -60
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
