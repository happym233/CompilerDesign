entry
          addi r14, r0, topaddr
          % processing pointer
          addi r1, r0, -252
          add r1, r1, r14
          sw -4(r14), r1
          % processing line4
          % processing line4
          % processing line4
          % processing: tmpVar2 := 0
          addi r1,r0,0
          sw -16(r14),r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-12(r14)
          sw -8(r14), r1
          % processing line4
          % processing line4
          % processing line4
          % processing: tmpVar5 := 0
          addi r1,r0,0
          sw -28(r14),r1
          % processing: tmpVar4 := tmpVar5
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar3 := tmpVar4
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing line4
          % processing line4
          % processing line4
          % processing line4
          % processing: tmpVar9 := 64
          addi r1,r0,64
          sw -44(r14),r1
          % processing: tmpVar8 := tmpVar9
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar7 := tmpVar8
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing: tmpVar6 := tmpVar7
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-32(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -8(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 2 
          % processing: dimension 1
          lw r3, -20(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 2 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line5
          % processing line5
          % processing line5
          % processing: tmpVar12 := 0
          addi r1,r0,0
          sw -56(r14),r1
          % processing: tmpVar11 := tmpVar12
          lw r1,-56(r14)
          sw -52(r14), r1
          % processing: tmpVar10 := tmpVar11
          lw r1,-52(r14)
          sw -48(r14), r1
          % processing line5
          % processing line5
          % processing line5
          % processing: tmpVar15 := 1
          addi r1,r0,1
          sw -68(r14),r1
          % processing: tmpVar14 := tmpVar15
          lw r1,-68(r14)
          sw -64(r14), r1
          % processing: tmpVar13 := tmpVar14
          lw r1,-64(r14)
          sw -60(r14), r1
          % processing line5
          % processing line5
          % processing line5
          % processing line5
          % processing: tmpVar19 := 34
          addi r1,r0,34
          sw -84(r14),r1
          % processing: tmpVar18 := tmpVar19
          lw r1,-84(r14)
          sw -80(r14), r1
          % processing: tmpVar17 := tmpVar18
          lw r1,-80(r14)
          sw -76(r14), r1
          % processing: tmpVar16 := tmpVar17
          lw r1,-76(r14)
          sw -72(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-72(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -48(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 2 
          % processing: dimension 1
          lw r3, -60(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 2 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line6
          % processing line6
          % processing line6
          % processing: tmpVar22 := 1
          addi r1,r0,1
          sw -96(r14),r1
          % processing: tmpVar21 := tmpVar22
          lw r1,-96(r14)
          sw -92(r14), r1
          % processing: tmpVar20 := tmpVar21
          lw r1,-92(r14)
          sw -88(r14), r1
          % processing line6
          % processing line6
          % processing line6
          % processing: tmpVar25 := 0
          addi r1,r0,0
          sw -108(r14),r1
          % processing: tmpVar24 := tmpVar25
          lw r1,-108(r14)
          sw -104(r14), r1
          % processing: tmpVar23 := tmpVar24
          lw r1,-104(r14)
          sw -100(r14), r1
          % processing line6
          % processing line6
          % processing line6
          % processing line6
          % processing: tmpVar29 := 25
          addi r1,r0,25
          sw -124(r14),r1
          % processing: tmpVar28 := tmpVar29
          lw r1,-124(r14)
          sw -120(r14), r1
          % processing: tmpVar27 := tmpVar28
          lw r1,-120(r14)
          sw -116(r14), r1
          % processing: tmpVar26 := tmpVar27
          lw r1,-116(r14)
          sw -112(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-112(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -88(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 2 
          % processing: dimension 1
          lw r3, -100(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 2 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line7
          % processing line7
          % processing line7
          % processing: tmpVar32 := 1
          addi r1,r0,1
          sw -136(r14),r1
          % processing: tmpVar31 := tmpVar32
          lw r1,-136(r14)
          sw -132(r14), r1
          % processing: tmpVar30 := tmpVar31
          lw r1,-132(r14)
          sw -128(r14), r1
          % processing line7
          % processing line7
          % processing line7
          % processing: tmpVar35 := 1
          addi r1,r0,1
          sw -148(r14),r1
          % processing: tmpVar34 := tmpVar35
          lw r1,-148(r14)
          sw -144(r14), r1
          % processing: tmpVar33 := tmpVar34
          lw r1,-144(r14)
          sw -140(r14), r1
          % processing line7
          % processing line7
          % processing line7
          % processing line7
          % processing: tmpVar39 := 12
          addi r1,r0,12
          sw -164(r14),r1
          % processing: tmpVar38 := tmpVar39
          lw r1,-164(r14)
          sw -160(r14), r1
          % processing: tmpVar37 := tmpVar38
          lw r1,-160(r14)
          sw -156(r14), r1
          % processing: tmpVar36 := tmpVar37
          lw r1,-156(r14)
          sw -152(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-152(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -128(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 2 
          % processing: dimension 1
          lw r3, -140(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 2 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line8
          % processing write statement
          % processing line8
          % processing line8
          % processing line8
          % processing line8
          % processing line8
          % processing line8
          % processing line8
          % processing: tmpVar46 := 0
          addi r1,r0,0
          sw -192(r14),r1
          % processing: tmpVar45 := tmpVar46
          lw r1,-192(r14)
          sw -188(r14), r1
          % processing: tmpVar44 := tmpVar45
          lw r1,-188(r14)
          sw -184(r14), r1
          % processing line8
          % processing line8
          % processing line8
          % processing: tmpVar49 := 0
          addi r1,r0,0
          sw -204(r14),r1
          % processing: tmpVar48 := tmpVar49
          lw r1,-204(r14)
          sw -200(r14), r1
          % processing: tmpVar47 := tmpVar48
          lw r1,-200(r14)
          sw -196(r14), r1
          addi r5, r14, 0
          % processing: tmpVar43 := arr
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -184(r5)
          add r1, r1, r3
          mul r1, r1, r2 
          muli r2, r2, 2 
          % processing: dimension 1
          lw r3, -196(r5)
          add r1, r1, r3
          mul r1, r1, r2 
          muli r2, r2, 2 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          lw r3, 0(r1)
          sw -180(r14), r3
          % processing: tmpVar42 := tmpVar43
          lw r1,-180(r14)
          sw -176(r14), r1
          % processing line8
          % processing line8
          % processing line8
          % processing line8
          % processing line8
          % processing: tmpVar55 := 1
          addi r1,r0,1
          sw -228(r14),r1
          % processing: tmpVar54 := tmpVar55
          lw r1,-228(r14)
          sw -224(r14), r1
          % processing: tmpVar53 := tmpVar54
          lw r1,-224(r14)
          sw -220(r14), r1
          % processing line8
          % processing line8
          % processing line8
          % processing: tmpVar58 := 1
          addi r1,r0,1
          sw -240(r14),r1
          % processing: tmpVar57 := tmpVar58
          lw r1,-240(r14)
          sw -236(r14), r1
          % processing: tmpVar56 := tmpVar57
          lw r1,-236(r14)
          sw -232(r14), r1
          addi r5, r14, 0
          % processing: tmpVar52 := arr
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -220(r5)
          add r1, r1, r3
          mul r1, r1, r2 
          muli r2, r2, 2 
          % processing: dimension 1
          lw r3, -232(r5)
          add r1, r1, r3
          mul r1, r1, r2 
          muli r2, r2, 2 
          muli r1, r1, -1
          lw r4, -4(r5)
          add r1, r4, r1
          lw r3, 0(r1)
          sw -216(r14), r3
          % processing: tmpVar51 := tmpVar52
          lw r1,-216(r14)
          sw -212(r14), r1
          % processing: tmpVar41 := tmpVar42
          lw r1,-176(r14)
          sw -172(r14), r1
          % processing: tmpVar41 := tmpVar41 + tmpVar51
          lw r1,-172(r14)
          lw r2,-212(r14)
          add r1,r1,r2
          sw -172(r14),r1
          % processing: tmpVar40 := tmpVar41
          lw r1,-172(r14)
          sw -168(r14), r1
          lw r1, -168(r14)
          addi r14, r14, -268
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -268
          addi r1, r0, charLineSep
          addi r14, r14, -268
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -268
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
