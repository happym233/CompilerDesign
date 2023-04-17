entry
          addi r14, r0, topaddr
          % processing line4
          % processing line4
          % processing line4
          % processing line4
          % processing: tmpVar3 := 1.0
          addi r1,r0,1
          sw -36(r14),r1
          % processing: tmpVar3 := 1.0
          addi r1,r0,0
          sw -40(r14),r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-36(r14)
          lw r2,-40(r14)
          sw -28(r14), r1
          sw -32(r14), r2
          % processing: tmpVar1 := tmpVar2
          lw r1,-28(r14)
          lw r2,-32(r14)
          sw -20(r14), r1
          sw -24(r14), r2
          % processing: tmpVar0 := tmpVar1
          lw r1,-20(r14)
          lw r2,-24(r14)
          sw -12(r14), r1
          sw -16(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-12(r5)
          lw r7,-16(r5)
          sw -4(r5), r6
          sw -8(r5), r7
          % processing line5
          % processing write statement
          % processing line5
          % processing line5
          % processing line5
          % processing line5
          addi r5, r14, 0
          % processing: tmpVar7 := counter
          lw r1,-4(r5)
          lw r2,-8(r5)
          sw -68(r5), r1
          sw -72(r5), r2
          % processing line5
          % processing: tmpVar9 := 2.0
          addi r1,r0,2
          sw -84(r14),r1
          % processing: tmpVar9 := 2.0
          addi r1,r0,0
          sw -88(r14),r1
          % processing: tmpVar6 := tmpVar7
          lw r1,-68(r14)
          lw r2,-72(r14)
          sw -60(r14), r1
          sw -64(r14), r2
          lw r1,-60(r14)
          lw r2,-64(r14)
          lw r3,-84(r14)
          lw r4,-88(r14)
          sw -160(r14), r1
          sw -164(r14), r2
          sw -168(r14), r3
          sw -172(r14), r4
          addi r14, r14, -156
          jl r15, floatMul
          lw r1, -4(r14)
          lw r2, -8(r14)
          subi r14, r14, -156
          sw -60(r14), r1
          sw -64(r14), r2
          % processing: tmpVar5 := tmpVar6
          lw r1,-60(r14)
          lw r2,-64(r14)
          sw -52(r14), r1
          sw -56(r14), r2
          % processing: tmpVar4 := tmpVar5
          lw r1,-52(r14)
          lw r2,-56(r14)
          sw -44(r14), r1
          sw -48(r14), r2
          lw r1, -44(r14)
          addi r14, r14, -156
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -156
          addi r1, r0, charE
          addi r14, r14, -156
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -156
          lw r1, -48(r14)
          addi r14, r14, -156
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -156
          addi r1, r0, charLineSep
          addi r14, r14, -156
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -156
          % processing line6
          % processing write statement
          % processing line6
          % processing line6
          % processing line6
          % processing line6
          addi r5, r14, 0
          % processing: tmpVar13 := counter
          lw r1,-4(r5)
          lw r2,-8(r5)
          sw -112(r5), r1
          sw -116(r5), r2
          % processing: tmpVar12 := tmpVar13
          lw r1,-112(r14)
          lw r2,-116(r14)
          sw -104(r14), r1
          sw -108(r14), r2
          % processing: tmpVar11 := tmpVar12
          lw r1,-104(r14)
          lw r2,-108(r14)
          sw -96(r14), r1
          sw -100(r14), r2
          % processing line6
          % processing line6
          % processing line6
          % processing: tmpVar17 := 1.2
          addi r1,r0,12
          sw -140(r14),r1
          % processing: tmpVar17 := 1.2
          addi r1,r0,1
          sw -144(r14),r1
          % processing: tmpVar16 := tmpVar17
          lw r1,-140(r14)
          lw r2,-144(r14)
          sw -132(r14), r1
          sw -136(r14), r2
          % processing: tmpVar15 := tmpVar16
          lw r1,-132(r14)
          lw r2,-136(r14)
          sw -124(r14), r1
          sw -128(r14), r2
          % processing: tmpVar10 := tmpVar11
          lw r1,-96(r14)
          sw -92(r14), r1
          lw r1,-92(r14)
          lw r2,-96(r14)
          lw r3,-124(r14)
          lw r4,-128(r14)
          sw -160(r14), r1
          sw -164(r14), r2
          sw -168(r14), r3
          sw -172(r14), r4
          addi r14, r14, -156
          jl r15, floatLeq
          lw r1, -4(r14)
          lw r2, -8(r14)
          subi r14, r14, -156
          sw -92(r14), r1
          sw -96(r14), r2
          lw r1, -92(r14)
          addi r14, r14, -156
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -156
          addi r1, r0, charLineSep
          addi r14, r14, -156
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -156
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
